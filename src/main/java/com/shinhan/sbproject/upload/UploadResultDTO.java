package com.shinhan.sbproject.upload;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@AllArgsConstructor
public class UploadResultDTO  implements Serializable {
    private static final long serialVersionUID = 1L;
	private String fileName;
    private String uuid;
    private String folderPath;
    
   /*Jackson은 Java Bean 규약을 따라 getXxx() 메서드를 찾아서 직렬화 시 JSON 속성 "xxx"로 변환
     getImageURL() → JSON 키 "imageURL",
     getThumbnailURL() → JSON 키 "thumbnailURL" 생성.
     DTO 필드에 실제로 imageURL 변수가 없어도, getter만 있으면 JSON에 포함
   */
    public String getImageURL(){
        try {
            return URLEncoder.encode(folderPath+"/"+uuid+"_"+fileName,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
    public String getThumbnailURL(){
        try {
            return URLEncoder.encode(folderPath+"/s_"+uuid+"_"+fileName,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    } 
}




