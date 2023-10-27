package com.novus.xmlmain.service;

import com.novus.xmlmain.exception.ConvertErrorException;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {

    @Override
    public String convertXmlToJson(MultipartFile file) {
        try {
            String xmlSource = new String(file.getBytes());
            JSONObject jsonObject = XML.toJSONObject(xmlSource);
            JSONObject jsonTree = createJsonTree(jsonObject.getJSONObject("root"));

            return jsonTree.toString();
        } catch (IOException e) {
            throw new ConvertErrorException("Convert error file: The file is damaged or impossible to process");
        }
    }

    private JSONObject createJsonTree(JSONObject root) {
        return null;
    }
}
