package com.application.job.util;

import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.application.job.model.entity.BaseEntity;
import com.application.job.util.exception.ZException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
	
	public static <E> JSONArray objectArray(List<E> objects) throws JSONException
    {
            if(objects.isEmpty() || objects==null)
            	return new JSONArray();

            JSONArray objArray = new JSONArray();

            for(E object : objects)
            {
                try
                {
                	Gson gson = new GsonBuilder().create();
                    String json = gson.toJson(object);
                    JSONObject obj = new JSONObject(json);
                    objArray.put(obj);
                } catch (JSONException e) {
                    try {
                        throw new ZException("Error", e);
                    } catch (ZException e1) {
                        e1.printStackTrace();
                    }
        		}
            }

            return objArray;
    }
	
	public static <E extends BaseEntity> JSONObject jsonObject(Object object)
	{
		if(object==null)
			return new JSONObject();
		
		JSONObject obj = new JSONObject();
		
		try
		{
			Gson gson = new GsonBuilder().create();
            String json = gson.toJson(object);
            obj = new JSONObject(json);
		} catch (JSONException e) {
            try {
                throw new ZException("Error", e);
            } catch (ZException e1) {
                e1.printStackTrace();
            }
		}
		
		return obj;
	}
}