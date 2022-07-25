package com.example.gifapp.classes;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SingleObj {

    private static SingleObj instance;
    private RequestQueue requestQueue;
    private final Context context;

    private SingleObj(Context context1) {
        context = context1;
        requestQueue = getRequestQueue();
    }

    public static SingleObj getInstance(Context context) {
        if (instance == null) {
            instance = new SingleObj(context);
        }
        return instance;
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }

}
