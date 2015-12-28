package com.sniff.main.actions.init_base_sniffers.templates;

/**
 * Created by ivan on 7.12.15.
 */
public class Templates {
    public static final String sResourcesErrorsStringsInit = "<resources>\n" +
            "    <!-- Sniff error strings (default - cz) -->\n" +
            "    <string name=\"default_error\">Došlo k neočekávané chybě, za způsobené komplikace se omlouváme.</string>\n" +
            "    <string name=\"connection_error\">Došlo k chybě připojení. Zkontrolujte prosím připojení k Internetu a zkuste to znovu.</string>\n" +
            "    <string name=\"server_error\">Došlo k chybě komunikace se serverem. Zkuste to prosím později.</string>\n\n";
    public static final String sResponseListenerBody = "package #c:package#.model.sniff.core;\n" +
            "\n" +
            "import #c:package#.model.sniff.core.ApplicationException;\n" +
            "\n" +
            "public interface ResponseListener<T> {\n" +
            "\n" +
            "    public void onResponse(T data);\n" +
            "\n" +
            "    public void onException(ApplicationException ex);\n" +
            "}\n";

    public static final String sStringResponseParserBode = "package #c:package#.model.sniff.parser;\n" +
            "\n" +
            "import #c:package#.model.sniff.core.ApplicationException;\n" +
            "\n" +
            "public interface StringResponseParser<T> {\n" +
            "\n" +
            "    public T parseResponse(String jsonObject) throws ApplicationException;\n" +
            "\n" +
            "}\n";

    public static final String sResponseParserBody = "package #c:package#.model.sniff.parser;\n" +
            "\n" +
            "import org.json.JSONObject;\n" +
            "\n" +
            "import #c:package#.model.sniff.core.ApplicationException;\n" +
            "\n" +
            "public abstract interface ResponseParser<T> {\n" +
            "\n" +
            "    public T parseResponse(JSONObject jsonObject) throws ApplicationException;\n" +
            "\n" +
            "}\n";

    public static final String sHandledStringRequestBody = "package #c:package#.model.sniff.request;\n" +
            "\n" +
            "import java.nio.charset.Charset;\n" +
            "import com.android.volley.Response;\n" +
            "import com.android.volley.VolleyError;\n" +
            "import com.android.volley.toolbox.StringRequest;\n" +
            "\n" +
            "import #c:package#.model.sniff.RequestHandler;\n" +
            "import #c:package#.model.sniff.core.ApplicationException;\n" +
            "import #c:package#.model.sniff.core.ResponseListener;\n" +
            "import #c:package#.model.sniff.parser.StringResponseParser;\n" +
            "import #c:package#.model.sniff.core.VolleyUtils;\n" +
            "\n" +
            "public class HandledStringRequest<T> extends StringRequest {\n" +
            "\n" +
            "    private String mParams;\n" +
            "\n" +
            "    public HandledStringRequest(int method, String tag, String url, String params, ResponseListener<T> listener, StringResponseParser<T> parser) {\n" +
            "        super(method, url, new AdapterListener<T>(listener, parser, tag), new AdapterErrorListener<T>(listener, tag));\n" +
            "        mParams = params;\n" +
            "        setTag(tag);\n" +
            "    }\n" +
            "\n" +
            "    public void setTag(String tag) {\n" +
            "        if (tag == null || tag.length() == 0) {\n" +
            "            throw new RuntimeException(\"Tag cannot be null or empty\");\n" +
            "        }\n" +
            "        super.setTag(tag);\n" +
            "    }\n" +
            "\n" +
            "    private static class AdapterListener<T> implements Response.Listener<String> {\n" +
            "\n" +
            "        private final ResponseListener<T> mListener;\n" +
            "        private final StringResponseParser<T> mParser;\n" +
            "        private final String mTag;\n" +
            "\n" +
            "        public AdapterListener(ResponseListener<T> listener, StringResponseParser<T> parser, String tag) {\n" +
            "            mListener = listener;\n" +
            "            mParser = parser;\n" +
            "            mTag = tag;\n" +
            "        }\n" +
            "\n" +
            "        @Override\n" +
            "        public void onResponse(String response) {\n" +
            "            try {\n" +
            "\n" +
            "                T o = mParser.parseResponse(response);\n" +
            "                mListener.onResponse(o);\n" +
            "            } catch (ApplicationException ex) {\n" +
            "                mListener.onException(ex);\n" +
            "                RequestHandler.notifyTaskError(mTag, ex);\n" +
            "            } finally {\n" +
            "                RequestHandler.notifyTaskStopped(mTag);\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "    private static class AdapterErrorListener<T> implements Response.ErrorListener {\n" +
            "\n" +
            "        private final ResponseListener<T> mListener;\n" +
            "        private final String mTag;\n" +
            "\n" +
            "        public AdapterErrorListener(ResponseListener<T> listener, String tag) {\n" +
            "            mListener = listener;\n" +
            "            mTag = tag;\n" +
            "        }\n" +
            "\n" +
            "        @Override\n" +
            "        public void onErrorResponse(VolleyError error) {\n" +
            "            // Handle error\n" +
            "            ApplicationException ex = VolleyUtils.convertException(error);\n" +
            "            RequestHandler.notifyTaskError(mTag, ex);\n" +
            "            RequestHandler.notifyTaskStopped(mTag);\n" +
            "            mListener.onException(ex);\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public byte[] getBody() {\n" +
            "        return mParams.getBytes(Charset.forName(\"UTF-8\"));\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public String getBodyContentType() {\n" +
            "        return \"application/x-www-form-urlencoded\";\n" +
            "    }\n" +
            "}\n";

    public static final String sHandledRequestBody = "package #c:package#.model.sniff.request;\n" +
            "\n" +
            "import java.nio.charset.Charset;\n" +
            "import com.android.volley.Request;\n" +
            "import com.android.volley.Response;\n" +
            "import com.android.volley.VolleyError;\n" +
            "import com.android.volley.toolbox.JsonObjectRequest;\n" +
            "\n" +
            "import org.json.JSONObject;\n" +
            "\n" +
            "import #c:package#.model.sniff.RequestHandler;\n" +
            "import #c:package#.model.sniff.core.ApplicationException;\n" +
            "import #c:package#.model.sniff.core.ResponseListener;\n" +
            "import #c:package#.model.sniff.parser.ResponseParser;\n" +
            "import #c:package#.model.sniff.core.VolleyUtils;\n" +
            "\n" +
            "public class HandledRequest<T> extends JsonObjectRequest {\n" +
            "\n" +
            "    private String mParams;\n" +
            "\n" +
            "    public HandledRequest(int method, String tag, String url, String params, ResponseListener<T> listener, ResponseParser<T> parser) {\n" +
            "        super(method, url, \"\", new AdapterListener<T>(listener, parser, tag), \n" +
            "                new AdapterErrorListener<T>(listener, tag));\n" +
            "        mParams = params;\n" +
            "        setTag(tag);\n" +
            "    }\n" +
            "\n" +
            "    public void setTag(String tag) {\n" +
            "        if (tag == null || tag.length() == 0) {\n" +
            "            throw new RuntimeException(\"Tag cannot be null or empty\");\n" +
            "        }\n" +
            "        super.setTag(tag);\n" +
            "    }\n" +
            "\n" +
            "    private static class AdapterListener<T> implements Response.Listener<JSONObject> {\n" +
            "\n" +
            "        private final ResponseListener<T> mListener;\n" +
            "        private final ResponseParser<T> mParser;\n" +
            "        private final String mTag;\n" +
            "\n" +
            "        public AdapterListener(ResponseListener<T> listener, ResponseParser<T> parser, String tag) {\n" +
            "            mListener = listener;\n" +
            "            mParser = parser;\n" +
            "            mTag = tag;\n" +
            "        }\n" +
            "\n" +
            "        @Override\n" +
            "        public void onResponse(JSONObject response) {\n" +
            "            try {\n" +
            "\n" +
            "                T o = mParser.parseResponse(response);\n" +
            "                mListener.onResponse(o);\n" +
            "            } catch (ApplicationException ex) {\n" +
            "                mListener.onException(ex);\n" +
            "                RequestHandler.notifyTaskError(mTag, ex);\n" +
            "            } finally {\n" +
            "                RequestHandler.notifyTaskStopped(mTag);\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "    private static class AdapterErrorListener<T> implements Response.ErrorListener {\n" +
            "\n" +
            "        private final ResponseListener<T> mListener;\n" +
            "        private final String mTag;\n" +
            "\n" +
            "        public AdapterErrorListener(ResponseListener<T> listener, String tag) {\n" +
            "            mListener = listener;\n" +
            "            mTag = tag;\n" +
            "        }\n" +
            "\n" +
            "        @Override\n" +
            "        public void onErrorResponse(VolleyError error) {\n" +
            "            // Handle error\n" +
            "            ApplicationException ex = VolleyUtils.convertException(error);\n" +
            "            RequestHandler.notifyTaskError(mTag, ex);\n" +
            "            RequestHandler.notifyTaskStopped(mTag);\n" +
            "            mListener.onException(ex);\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public byte[] getBody() {\n" +
            "        return mParams.getBytes(Charset.forName(\"UTF-8\"));\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public String getBodyContentType() {\n" +
            "        return \"application/x-www-form-urlencoded\";\n" +
            "    }\n" +
            "}\n";

    public static final String sVolleyUtilsBody = "package #c:package#.model.sniff.core;\n" +
            "\n" +
            "import android.content.Context;\n" +
            "\n" +
            "import com.android.volley.AuthFailureError;\n" +
            "import com.android.volley.NetworkError;\n" +
            "import com.android.volley.NoConnectionError;\n" +
            "import com.android.volley.ServerError;\n" +
            "import com.android.volley.TimeoutError;\n" +
            "import com.android.volley.VolleyError;\n" +
            "\n" +
            "import #c:package#.R;\n" +
            "import #c:package#.model.sniff.core.ApplicationException;\n" +
            "\n" +
            "public class VolleyUtils {\n" +
            "\n" +
            "    public static ApplicationException convertException(VolleyError volleyError) {\n" +
            "        return new ApplicationException(volleyError.getMessage(), getErrorMessageId(volleyError));\n" +
            "    }\n" +
            "\n" +
            "    public static String getErrorMessage(Object volleyError, Context c) {\n" +
            "        return c.getString(getErrorMessageId(volleyError));\n" +
            "    }\n" +
            "\n" +
            "    public static int getErrorMessageId(Object volleyError) {\n" +
            "        if (volleyError instanceof TimeoutError) {\n" +
            "            // Server down?\n" +
            "            return R.string.server_error;\n" +
            "        } else if (isServerProblem(volleyError)) {\n" +
            "            return R.string.server_error;\n" +
            "        } else if (isNetworkProblem(volleyError)) {\n" +
            "            // No network\n" +
            "            return R.string.connection_error;\n" +
            "        } else {\n" +
            "            // Unknown error\n" +
            "            return R.string.default_error;\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "    private static boolean isNetworkProblem(Object error) {\n" +
            "        return (error instanceof NetworkError) || (error instanceof NoConnectionError);\n" +
            "    }\n" +
            "\n" +
            "    private static boolean isServerProblem(Object error) {\n" +
            "        return (error instanceof ServerError) || (error instanceof AuthFailureError);\n" +
            "    }\n" +
            "}\n";

    public static final  String sUrlConfigBody = "package #c:package#.model.utils;\n" +
            "\n" +
            "public class UrlConfig {\n" +
            "\t//TODO Add url constants (for each method, named according to method name, Ex: URL_LOAD_<method_name_capitalized>S, also can be found in correspond request)\n" +
            "}\n";

    public static final String sApplicationExceptionBody = "package #c:package#.model.sniff.core;\n" +
            "\n" +
            "import #c:package#.R;\n" +
            "\n" +
            "public class ApplicationException extends Exception {\n" +
            "\n" +
            "    private int mUserMessageId;\n" +
            "\n" +
            "    public ApplicationException(String devMessage) {\n" +
            "        super(devMessage);\n" +
            "\t//TODO Add string resource\n" +
            "        mUserMessageId = R.string.default_error;\n" +
            "    }\n" +
            "\n" +
            "    public ApplicationException(String devMessage, int userMessageId) {\n" +
            "        super(devMessage);\n" +
            "        mUserMessageId = userMessageId;\n" +
            "    }\n" +
            "\n" +
            "    public int getUserMessageId() {\n" +
            "        return mUserMessageId;\n" +
            "    }\n" +
            "\n" +
            "    public String getDevMessage() {\n" +
            "        return getMessage();\n" +
            "    }\n" +
            "}\n";

    public static final String sLoadingListenerBody = "package #c:package#.model.sniff.core;\n" +
            "\n" +
            "public interface LoadingListener {\n" +
            "\n" +
            "    public void onLoadingStarted(String taskTag);\n" +
            "\n" +
            "    public void onLoadingStopped(String taskTag);\n" +
            "\n" +
            "    public void onTaskError(String taskTag, int errorMessageId);\n" +
            "}\n";

    public static final String sRequestHandlerBody = "package #c:package#.model.sniff;\n" +
            "\n" +
            "import android.content.Context;\n" +
            "import android.graphics.Bitmap;\n" +
            "import android.support.v4.util.LruCache;\n" +
            "import android.util.Log;\n" +
            "\n" +
            "import com.android.volley.Request;\n" +
            "import com.android.volley.RequestQueue;\n" +
            "import com.android.volley.toolbox.ImageLoader;\n" +
            "import com.android.volley.toolbox.Volley;\n" +
            "\n" +
            "import java.io.IOException;\n" +
            "import java.security.KeyManagementException;\n" +
            "import java.security.KeyStoreException;\n" +
            "import java.security.NoSuchAlgorithmException;\n" +
            "import java.security.UnrecoverableKeyException;\n" +
            "import java.security.cert.CertificateException;\n" +
            "import java.util.ArrayList;\n" +
            "import java.util.HashMap;\n" +
            "\n" +
            "import #c:package#.model.sniff.core.ApplicationException;\n" +
            "import #c:package#.model.sniff.core.LoadingListener;\n" +
            "\n" +
            "public class RequestHandler {\n" +
            "\n" +
            "    private static RequestHandler sInstance = null;\n" +
            "\n" +
            "    public static void init(Context appContext) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyManagementException, KeyStoreException, IOException {\n" +
            "        sInstance = new RequestHandler(appContext);\n" +
            "    }\n" +
            "\n" +
            "    protected static RequestHandler get() {\n" +
            "        if (sInstance == null) {\n" +
            "            throw new RuntimeException(\"RequestHandler not configured. Use init() before using the class.\");\n" +
            "        }\n" +
            "        return sInstance;\n" +
            "    }\n" +
            "\n" +
            "    public static void execute(Request request) {\n" +
            "        get().handleExecRequest(request);\n" +
            "    }\n" +
            "\n" +
            "    public static void addListener(String tag, LoadingListener listener) {\n" +
            "        get().handleAddListener(tag, listener);\n" +
            "    }\n" +
            "\n" +
            "    public static void removeListener(String tag, LoadingListener listener) {\n" +
            "        get().handleRemoveListener(tag, listener);\n" +
            "    }\n" +
            "\n" +
            "    public static void notifyTaskStarted(String requestTag) {\n" +
            "        get().handleNotifyTaskStarted(requestTag);\n" +
            "    }\n" +
            "\n" +
            "    public static void notifyTaskStopped(String requestTag) {\n" +
            "        get().handleNotifyAllTasksStopped(requestTag);\n" +
            "    }\n" +
            "\n" +
            "    public static void notifyTaskError(String requestTag, int errorMessage) {\n" +
            "        get().handleNotifyError(requestTag, errorMessage);\n" +
            "    }\n" +
            "\n" +
            "    public static void notifyTaskError(String requestTag, ApplicationException ex) {\n" +
            "        get().handleNotifyError(requestTag, ex.getUserMessageId());\n" +
            "    }\n" +
            "\n" +
            "    public static ImageLoader getImageLoader() {\n" +
            "        return get().mImageLoader;\n" +
            "    }\n" +
            "\n" +
            "    private RequestQueue mQueue;\n" +
            "    private ImageLoader mImageLoader;\n" +
            "    private HashMap<String, ArrayList<LoadingListener>> mListenerMap;\n" +
            "    private HashMap<String, LoadingCounter> mCounterMap;\n" +
            "    private ArrayList<LoadingListener> mNullListeners;\n" +
            "\n" +
            "\n" +
            "    private RequestHandler(Context appContext) throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyManagementException, KeyStoreException, IOException {\n" +
            "        mQueue = Volley.newRequestQueue(appContext);\n" +
            "        mListenerMap = new HashMap<String, ArrayList<LoadingListener>>();\n" +
            "        mCounterMap = new HashMap<String, LoadingCounter>();\n" +
            "        mNullListeners = new ArrayList<LoadingListener>();\n" +
            "        mImageLoader = new ImageLoader(mQueue, new ImageLoader.ImageCache() {\n" +
            "            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);\n" +
            "\n" +
            "            public void putBitmap(String url, Bitmap bitmap) {\n" +
            "                mCache.put(url, bitmap);\n" +
            "            }\n" +
            "\n" +
            "            public Bitmap getBitmap(String url) {\n" +
            "                return mCache.get(url);\n" +
            "            }\n" +
            "        });\n" +
            "    }\n" +
            "\n" +
            "    public void handleExecRequest(Request request) {\n" +
            "        if (request.getTag() == null) {\n" +
            "            throw new RuntimeException(\"Task tag cannot be null, use String\");\n" +
            "        }\n" +
            "        if (!(request.getTag() instanceof String)) {\n" +
            "            throw new RuntimeException(\"Task tag has to be String\");\n" +
            "        } else if (((String) request.getTag()).length() == 0) {\n" +
            "            throw new RuntimeException(\"Task tag cannot be empty String\");\n" +
            "        }\n" +
            "\n" +
            "        handleNotifyTaskStarted((String) request.getTag());\n" +
            "        mQueue.add(request);\n" +
            "    }\n" +
            "\n" +
            "    public void cancelRequestsByTag(String tag) {\n" +
            "        mQueue.cancelAll(tag);\n" +
            "        handleNotifyAllTasksStopped(tag);\n" +
            "    }\n" +
            "\n" +
            "    private void handleAddListener(String tag, LoadingListener listener) {\n" +
            "        if (tag == null) {\n" +
            "            if (!mNullListeners.contains(listener)) {\n" +
            "                mNullListeners.add(listener);\n" +
            "            } else {\n" +
            "                Log.w(\"LoadingManager\", \"Null listener added twice!\");\n" +
            "            }\n" +
            "        } else {\n" +
            "            if (!mListenerMap.containsKey(tag)) {\n" +
            "                mListenerMap.put(tag, new ArrayList<LoadingListener>());\n" +
            "            }\n" +
            "            mListenerMap.get(tag).add(listener);\n" +
            "            if (mCounterMap.containsKey(tag) && mCounterMap.get(tag).isLoading()) {\n" +
            "                listener.onLoadingStarted(tag);\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "    private void handleRemoveListener(String tag, LoadingListener listener) {\n" +
            "        if (tag == null) {\n" +
            "            // Remove the listener from ALL fields\n" +
            "            mNullListeners.remove(listener);\n" +
            "            for (ArrayList<LoadingListener> list : mListenerMap.values()) {\n" +
            "                list.remove(listener);\n" +
            "            }\n" +
            "        } else {\n" +
            "            if (mListenerMap.containsKey(tag)) {\n" +
            "                mListenerMap.get(tag).remove(listener);\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "    private int getLoadingTaskCount() {\n" +
            "        int result = 0;\n" +
            "        for (LoadingCounter c : mCounterMap.values()) {\n" +
            "            result += c.getCount();\n" +
            "        }\n" +
            "        return result;\n" +
            "    }\n" +
            "\n" +
            "    private boolean handleIsTaskRunning(String tag) {\n" +
            "        if (mCounterMap.containsKey(tag)) {\n" +
            "            return mCounterMap.get(tag).isLoading();\n" +
            "        } else {\n" +
            "            return false;\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "    /* ------- TASK NOTIFICATIONS ------- */\n" +
            "\n" +
            "    private void handleNotifyTaskStarted(String tag) {\n" +
            "        Log.d(\"RequestHandler\", \"Noting task started: \" + tag);\n" +
            "        if (tag == null) throw new RuntimeException(\"Null not allowed for tasks\");\n" +
            "        if (!mCounterMap.containsKey(tag)) {\n" +
            "            mCounterMap.put(tag, new LoadingCounter());\n" +
            "        }\n" +
            "        LoadingCounter counter = mCounterMap.get(tag);\n" +
            "        if (counter.increase()) {\n" +
            "            if (mListenerMap.containsKey(tag)) {\n" +
            "                for (LoadingListener l : mListenerMap.get(tag)) {\n" +
            "                    Log.d(\"RequestHandler\", \"Notifing listener\");\n" +
            "                    l.onLoadingStarted(tag);\n" +
            "                }\n" +
            "            }\n" +
            "        }\n" +
            "        Log.d(\"RequestHandler\", \"Loading count: \" + counter.getCount());\n" +
            "        if (getLoadingTaskCount() == 1) {\n" +
            "            for (LoadingListener l : mNullListeners) {\n" +
            "                l.onLoadingStarted(null);\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "    private void handleNotifyTaskStopped(String tag) {\n" +
            "        Log.d(\"RequestHandler\", \"HANDLE STOPPED: \" + tag);\n" +
            "        if (tag == null) throw new RuntimeException(\"Null not allowed for tasks\");\n" +
            "        if (!mCounterMap.containsKey(tag)) {\n" +
            "            Log.w(\"LoadingManager\", \"Task with tag \" + tag + \" stopped before starting\");\n" +
            "        } else {\n" +
            "            LoadingCounter counter = mCounterMap.get(tag);\n" +
            "            if (counter.decrease()) {\n" +
            "                if (mListenerMap.containsKey(tag)) {\n" +
            "                    for (LoadingListener l : mListenerMap.get(tag)) {\n" +
            "                        l.onLoadingStopped(tag);\n" +
            "                    }\n" +
            "                }\n" +
            "            }\n" +
            "\n" +
            "            if (getLoadingTaskCount() == 0) {\n" +
            "                for (LoadingListener l : mNullListeners) {\n" +
            "                    l.onLoadingStopped(null);\n" +
            "                }\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "    private void handleNotifyAllTasksStopped(String tag) {\n" +
            "        Log.d(\"RequestHandler\", \"HANDLE ALL STOPPED: \" + tag);\n" +
            "        if (tag == null) throw new RuntimeException(\"Null not allowed for tasks\");\n" +
            "        if (!mCounterMap.containsKey(tag)) {\n" +
            "            Log.w(\"LoadingManager\", \"Task with tag \" + tag + \" stopped before starting\");\n" +
            "        } else {\n" +
            "            mCounterMap.remove(tag);\n" +
            "\n" +
            "            ArrayList<LoadingListener> listeners = mListenerMap.get(tag);\n" +
            "            if (listeners != null) {\n" +
            "                for (LoadingListener l : listeners) {\n" +
            "                    l.onLoadingStopped(tag);\n" +
            "                }\n" +
            "            }\n" +
            "\n" +
            "            Log.d(\"LoadingManager\", \"All with tag \" + tag + \" stopped. Running:\" + getLoadingTaskCount());\n" +
            "            if (getLoadingTaskCount() == 0) {\n" +
            "                for (LoadingListener l : mNullListeners) {\n" +
            "                    l.onLoadingStopped(null);\n" +
            "                }\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "    // Null listeners are not notified of errors, notify them manually\n" +
            "    private void handleNotifyError(String tag, int messageId) {\n" +
            "        if (tag == null) throw new RuntimeException(\"Null not allowed for tasks\");\n" +
            "        if (mListenerMap.containsKey(tag)) {\n" +
            "            for (LoadingListener l : mListenerMap.get(tag)) {\n" +
            "                l.onTaskError(tag, messageId);\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "    private static class LoadingCounter {\n" +
            "        private int activeLoading;\n" +
            "\n" +
            "        LoadingCounter() {\n" +
            "            activeLoading = 0;\n" +
            "        }\n" +
            "\n" +
            "        boolean increase() {\n" +
            "            activeLoading++;\n" +
            "            return (activeLoading == 1);\n" +
            "        }\n" +
            "\n" +
            "        boolean decrease() {\n" +
            "            activeLoading--;\n" +
            "            if (activeLoading < 0) {\n" +
            "                Log.wtf(\"LoadingManager\", \"Loading went below zero!\");\n" +
            "            }\n" +
            "            return (activeLoading == 0);\n" +
            "        }\n" +
            "\n" +
            "        boolean isLoading() {\n" +
            "            return activeLoading > 0;\n" +
            "        }\n" +
            "\n" +
            "        int getCount() {\n" +
            "            return activeLoading;\n" +
            "        }\n" +
            "    }\n" +
            "}\n";
}
