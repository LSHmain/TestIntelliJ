package com.shinhan.sbproject.upload;


import com.shinhan.sbproject.entity.MemberEntity;
import com.shinhan.sbproject.entityfinal.ProfileEntity;
import com.shinhan.sbproject.repository.MemberRepository;
import com.shinhan.sbproject.repositoryfinal.ProfileRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

 

@RestController
@Log4j2
public class UploadController {
    @Value("${com.shinhan.sbproject.upload.path}")
    private String uploadPath;   //application.properties파일에 설정값을 읽어서 Injection

    @Autowired
    ProfileRepository profileRepo;
    
    @Autowired
    MemberRepository memberRepo;

    @PostMapping("/upload/uploadAjax")
    public ResponseEntity<List<UploadResultDTO>> uploadFile(MultipartFile[] uploadFiles, String mid){

    	MemberEntity member = MemberEntity.builder()
    			.mid(mid)
    			.build();
 
        List<UploadResultDTO> resultDTOList = new ArrayList<>();
        int i=0; 
        for (MultipartFile uploadFile: uploadFiles) {
            if(uploadFile.getContentType().startsWith("image") == false) {
                log.warn("this file is not image type");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }          
            
            String originalName = uploadFile.getOriginalFilename();//실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
            log.info("fileName: " + fileName);
            String folderPath = makeFolder(); //날짜 폴더 생성           
            String uuid = UUID.randomUUID().toString();//UUID
            //저장할 파일 이름 중간에 "_"를 이용해서 구분
            String saveName = uploadPath + File.separator + folderPath + File.separator + uuid +"_" + fileName;
            Path savePath = Paths.get(saveName);
            try {        
            	//Member가 없으면 Profile image upload불가 
            	if(memberRepo.findById(mid).orElse(null) == null) break;
            	
                uploadFile.transferTo(savePath);//원본 파일 저장  이 부분이 upload
                System.out.println("저장 경로: " + saveName);
                String thumbnailSaveName = uploadPath + File.separator + folderPath + File.separator
                        +"s_" + uuid +"_" + fileName; //섬네일 파일 이름은 중간에 s_로 시작하도록              
                File thumbnailFile = new File(thumbnailSaveName);                
                Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile,100,100 );//섬네일 생성

                System.out.println("저장 경로: " + saveName);
                System.out.println("UploadResultDTO: " + new UploadResultDTO(fileName,uuid,folderPath));


                resultDTOList.add(new UploadResultDTO(fileName,uuid,folderPath));
                
                //System.out.println(new UploadResultDTO(fileName,uuid,folderPath));

              //-------------------DB에 저장(하나의 멤버가 여러개의 Profile을 가짐)---------------------------------
              ProfileEntity entity =  ProfileEntity.builder()
            		  .fname(saveName)
            		  .member(member)
            		  .currentYn(i==uploadFiles.length-1?true:false)
            		  .build(); 
              profileRepo.save(entity);
              i++;
              //---------------------------------------------------------
            
            } catch (IOException e) { e.printStackTrace(); }
        }//end for



        return new ResponseEntity<>(resultDTOList, HttpStatus.OK);
    }

    private String makeFolder() {
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath =  str.replace("//", File.separator);  //File.separator 파일의 구분자로 변경 /
        // make folder --------
        File uploadPathFolder = new File(uploadPath, folderPath);

        if (uploadPathFolder.exists() == false) {
//            uploadPathFolder.mkdirs();
            boolean created = uploadPathFolder.mkdirs();
            System.out.println(">> 폴더 생성됨: " + uploadPathFolder.getPath() + ", 성공 여부: " + created);
        }
        return folderPath;
    }
    
    //서버에 저장된 파일(이미지 등)을 읽어서 바이너리로 응답
    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(String fileName, String size) {
        ResponseEntity<byte[]> result = null;
        try {
        	//URL 인코딩/디코딩 처리로 한글 파일명 안전하게 지원
            String srcFileName =  URLDecoder.decode(fileName,"UTF-8");
            File file = new File(uploadPath +File.separator+ srcFileName);
            //size=1이면 파일명 앞의 s_를 제거하여 원본 파일 선택
            //s_uuid_filename.jpg → uuid_filename.jpg
            if(size != null && size.equals("1")){
                file  = new File(file.getParent(), file.getName().substring(2));
            }
            HttpHeaders header = new HttpHeaders();
            //MIME타입 자동감지 (image/jpeg, image/png)
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            //파일 데이터를 바이트 배열로 읽어 응답
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }
    @Transactional
    @PostMapping("/removeFile")
    public ResponseEntity<Boolean> removeFile(String fileName){
        String srcFileName = null;
        try {
            srcFileName = URLDecoder.decode(fileName,"UTF-8");
            String fullName = uploadPath +File.separator+ srcFileName;
            File file = new File(uploadPath +File.separator+ srcFileName);
            boolean result = file.delete();
            File thumbnail = new File(file.getParent(), "s_" + file.getName());
            result = thumbnail.delete();
            System.out.println(fullName);
            //db에서도 삭제


            List<ProfileEntity> profiles = profileRepo.findProfileEntityByFname(fullName);
            if (profiles.size() == 1){
                System.out.println(profiles.get(0).getFname());
                profileRepo.delete(profiles.get(0));
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
