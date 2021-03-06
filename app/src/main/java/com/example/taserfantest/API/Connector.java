package com.example.taserfantest.API;


import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;


public class Connector {

    private static Connector connector;
    private static Conversor conversor;
    private static CallMethods callMethodsObject;

    public static Connector getConector() {
        if (connector == null) {
            connector = new Connector();
            conversor = Conversor.getConversor();
            callMethodsObject = CallMethods.getCallMethodsObject();
        }
        return connector;
    }

    public <T> List<T> getAsList(Class<T> clazz, String path) {
        String url = API.Routes.URL + path;
        String jsonResponse = callMethodsObject.get(url);
        if (jsonResponse != null)
            return conversor.fromJsonList(jsonResponse, clazz);
        return null;
    }

    public <T> List<T> postAsList(Class<T> clazz,Object data, String path) {
        try {
            String url = API.Routes.URL + path;
            String jsonObject = conversor.toJson(data);
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject);
            Response<ResponseBody> jsonResponse = callMethodsObject.postResult(url, body);
            if (jsonResponse != null)
                return conversor.fromJsonList(jsonResponse.body().string(), clazz);
        } catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }

    public <T> Result<T> iuCoche(Class<T> clazz, Object data, String path) {
        try {
            String url = API.Routes.URL + path;
            String jsonObject = conversor.toJson(data);
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject);
            Response<ResponseBody> jsonResponse = callMethodsObject.postResult(url, body);

            if (jsonResponse != null && jsonResponse.code() == 200)
                return conversor.fromJSonToSuccess(jsonResponse.body().string(), clazz);
            else if (jsonResponse != null)
                return conversor.getError(jsonResponse.errorBody().string());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> boolean eliminaVehiculo(Class<T> clazz,Object data, String path) {

            String url = API.Routes.URL + path;
            String jsonObject = conversor.toJson(data);
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject);
            Response<ResponseBody> jsonResponse = callMethodsObject.postResult(url, body);
            if (jsonResponse != null && jsonResponse.code() == 200)
                return true;
            else
                return false;

    }


//    public <T> T get(Class<T> clazz, String path) {
//        String url = API.Routes.URL + path;
//        String jsonResponse = callMethodsObject.get(url);
//        if (jsonResponse != null)
//            return conversor.fromJson(jsonResponse, clazz);
//        return null;
//    }

    public <T> Result<T> get(Class<T> clazz, String path) {
        try {
            String url = API.Routes.URL + path;
            Response<ResponseBody> jsonResponse = callMethodsObject.getResult(url);
            if (jsonResponse != null && jsonResponse.code() == 200)
                return conversor.fromJSonToSuccess(jsonResponse.body().string(), clazz);
            else if (jsonResponse != null)

                return conversor.getError(jsonResponse.errorBody().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> Result<T> post(Class<T> clazz, Object data, String path) {
        try {
            String url = API.Routes.URL + path;
            String jsonObject = conversor.toJson(data);
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject);
            Response<ResponseBody> jsonResponse = callMethodsObject.postResult(url, body);

            if (jsonResponse != null && jsonResponse.code() == 200)
                return conversor.fromJSonToSuccess(jsonResponse.body().string(), clazz);
            else if (jsonResponse != null)
                return conversor.getError(jsonResponse.errorBody().string());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> Result<T> selVehiculo(Class<T> clazz, String data, String path) {
        try {
            String url = API.Routes.URL + path + "?matricula=" + data;
            Response<ResponseBody> jsonResponse = callMethodsObject.getResult(url);
            if (jsonResponse != null && jsonResponse.code() == 200)
                return conversor.fromJSonToSuccess(jsonResponse.body().string(), clazz);
            else if (jsonResponse != null)

                return conversor.getError(jsonResponse.errorBody().string());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    public <T> Result<T> put(Class<T> clazz, T data, String path) {
        try {
            String url = API.Routes.URL + path;
            String jsonObject = conversor.toJson(data);
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonObject);
            Response<ResponseBody> jsonResponse = callMethodsObject.putResult(url, body);

            if (jsonResponse != null && jsonResponse.code() == 200)
                return conversor.fromJSonToSuccess(jsonResponse.body().string(), clazz);
            else if (jsonResponse != null)
                return conversor.getError(jsonResponse.errorBody().string());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> Result<T> delete(Class<T> clazz, String path) {
        try {
            String url = API.Routes.URL + path;
            Response<ResponseBody> jsonResponse = callMethodsObject.deleteResult(url);
            if (jsonResponse != null && jsonResponse.code() == 200)
                return conversor.fromJSonToSuccess(jsonResponse.body().string(), clazz);
            else if (jsonResponse != null) {
                return conversor.getError(jsonResponse.errorBody().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> Result<T> deleteVehiculo(Class<T> clazz, String data, String path) {
        try {
            String url = API.Routes.URL + path + "?matricula=" + data;
            Response<ResponseBody> jsonResponse = callMethodsObject.deleteResult(url);
            if (jsonResponse != null && jsonResponse.code() == 200)
                return conversor.fromJSonToSuccess(jsonResponse.body().string(), clazz);
            else if (jsonResponse != null) {
                return conversor.getError(jsonResponse.errorBody().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}