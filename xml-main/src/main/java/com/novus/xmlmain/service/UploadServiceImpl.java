package com.novus.xmlmain.service;

import com.novus.xmlmain.exception.ConvertErrorException;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {

    @Override
    public String convertXmlToJson(MultipartFile file) {
        try {
            String xmlSource = new String(file.getBytes());
            JSONObject jsonObject = XML.toJSONObject(xmlSource);

            JSONObject jsonTree = null;

            if (jsonObject.keySet().size() >= 1) {
                jsonTree = createJsonTree(jsonObject.getJSONObject("root"));

                JSONObject finalJson = new JSONObject();
                finalJson.put("root", jsonTree);

                return finalJson.toString();
            }

            jsonObject.put("value", "0");
            return jsonObject.toString();

        } catch (IOException | JSONException e) {
            throw new ConvertErrorException("Convert error file: The file is damaged or impossible to process");
        }
    }

    private JSONObject createJsonTree(JSONObject root) {
        JSONObject result = new JSONObject();

        double sum = 0;
        for (String key : root.keySet()) {
            Object value = root.get(key);
            if (value instanceof JSONObject) {
                JSONObject childNode = createJsonTree((JSONObject) value);
                sum += getChildNodeValue(childNode);
                result.put(key, childNode);
            } else {
                sum += calculateValueSum((String) value);
                result.put(key, createValueNode((String) value));
            }
        }


        result.put("value", String.valueOf(sum));


        return result;
    }

    private JSONObject createValueNode(String value) {
        JSONObject valueNode = new JSONObject();
        String lineValue = value.replaceAll("[^0-9.]", "");

        if (!lineValue.isEmpty()) {
            valueNode.put("value", lineValue);
        } else {
            valueNode.put("value", "0");
        }
        return valueNode;
    }

    private double getChildNodeValue(JSONObject node) {
        if (node.has("value")) {
            return Double.parseDouble(node.getString("value"));
        }

        return 0;
    }

    private double calculateValueSum(String value) {
        double sum = 0;
        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(value);
        while (matcher.find()) {
            sum += Double.parseDouble(matcher.group());
        }
        return sum;
    }
}