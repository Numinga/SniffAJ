package com.sniff.main.actions.add_new_model.templates;

/**
 * Created by ivan on 7.12.15.
 */
public class Templates {
    public static final String mParserBody = "package #c:model.path#.model.#c:name.l#.#c:fname.l#;\n" +
            "import org.json.JSONArray;\n" +
            "import org.json.JSONException;\n" +
            "import org.json.JSONObject;\n" +
            "\n" +
            "import #c:model.path#.model.sniff.core.ApplicationException;\n" +
            "import #c:model.path#.model.sniff.parser.ResponseParser;\n" +
            "\n" +
            "public class #c:name#Parser implements ResponseParser<#c:type#>{\n" +
            "\n" +
            "    @Override\n" +
            "    public #c:type# parseResponse(JSONObject jsonObject) throws ApplicationException {\n" +
            "       \t//TODO: method body\n" +
            "        return null;\n" +
            "    }\n" +
            "}\n";

    public static final String mRequestBody = "package #c:model.path#.model.#c:name.l#.#c:fname.l#;\n" +
            "import #c:model.path#.model.sniff.core.ResponseListener;\n" +
            "import #c:model.path#.model.sniff.request.HandledRequest;\n" +
            "import #c:model.path#.model.utils.UrlConfig;\n" +
            "import #c:model.path#.model.#c:name.l#.#c:model.name#Model;\n" +
            "\n" +
            "public class #c:name#Request extends HandledRequest {\n" +
            "\n" +
            "    public #c:name#Request(int method, String params, ResponseListener<#c:type#> r) {\n" +
            "        super(method, #c:model.name#Model.TAG_#c:name.h#, UrlConfig.URL_LOAD_#c:name.h#S, params, r, new #c:name#Parser());\n" +
            "    }\n" +
            "}\n";

    public static final String mListenerBody = "package #c:model.path#.model.#c:name.l#.#c:fname.l#;\n" +
            "import #c:model.path#.model.sniff.core.LoadingListener;\n" +
            "\n" +
            "public interface #c:name#LoadingListener extends LoadingListener {\n" +
            "    void on#c:name#Loaded(#c:type# data);\n" +
            "}\n";

    public static final String mModelBody = "package #c:model.path#.model.#c:name.l#;\n" +
            "import android.content.Context;\n" +
            "import android.util.Log;\n" +
            "import java.util.ArrayList;\n" +
            "#c:import#\n" +
            "import com.android.volley.Request;\n" +
            "import #c:model.path#.model.sniff.RequestHandler;\n" +
            "import #c:model.path#.model.sniff.core.ApplicationException;\n" +
            "import #c:model.path#.model.sniff.core.ResponseListener;\n" +
            "\n" +
            "public class #c:name#Model {\n" +
            "    private static #c:name#Model sInstance;\n" +
            "#c:listeners.declare#\n" +
            "    \n" +
            "// ----- Singleton creation ----- \n" +
            "    private static #c:name#Model getInstance() {\n" +
            "        if (sInstance == null) {\n" +
            "            throw new RuntimeException(\"#c:name#Model not initialized, call init() first\");\n" +
            "        }\n" +
            "        return sInstance;\n" +
            "    }\n" +
            "\n" +
            "    public static void init(Context context) {\n" +
            "        sInstance = new #c:name#Model(context);\n" +
            "    }\n" +
            "\n" +
            "// ----- Setters and removers for listeners ----- \n" +
            "#c:listeners.set#\n" +
            "\n" +
            "// ----- Static loading method declare ----- \n" +
            "#c:method.static#\n" +
            "\n" +
            "    private #c:name#Model(Context c) {\n" +
            "        #c:listeners.init#" +
            "    }\n" +
            "\n" +
            "// ----- Loading method bodies ----- \n" +
            "#c.method.body#\n" +
            "\n" +
            "// ----- Data loaded notify ----- \n" +
            "#c.method.notify#\n" +
            "}\n";





    public static final String mMethodListenerDeclare = "    public static String TAG_#c:name.h# = \"#c:name.l#_request\";\n" +
            "    private static ArrayList <#c:name#LoadingListener> m#c:name#Listeners;\n";
    public static final String mMethodListenerSet = "    public static void add#c:name#Listener(#c:name#LoadingListener l) {\n" +
            "        if (!getInstance().m#c:name#Listeners.contains(l)) {\n" +
            "            getInstance().m#c:name#Listeners.add(l);\n" +
            "            RequestHandler.addListener(TAG_#c:name.h#, l);\n" +
            "        } else {\n" +
            "            Log.w(\"#c:name.model#Model\", \"Listener already added: #c:name#LoadingListener\");\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "    public static void remove#c:name#Listener(#c:name#LoadingListener l) {\n" +
            "        if (!getInstance().m#c:name#Listeners.remove(l)) {\n" +
            "            Log.w(\"#c:name.model#Model\", \"Removed listener not added: #c:name#LoadingListener\");\n" +
            "        }\n" +
            "        getInstance().m#c:name#Listeners.remove(l);\n" +
            "        RequestHandler.removeListener(TAG_#c:name.h#, l);\n" +
            "    }\n";
    public static final String mMethodListenerInit = "    m#c:name#Listeners = new ArrayList<#c:name#LoadingListener>();\n";
    public static final String mMethodStatic = "    public static void load#c:name#(){\n" +
            "        getInstance().startLoading#c:name#();\n" +
            "    }\n";
    public static final String mMethodBody = "    public void startLoading#c:name#(){\n" +
            "        String params = null;\n" +
            "        #c:name#Request r = new #c:name#Request(Request.Method.GET, params, new ResponseListener<#c:type#>() {\n" +
            "            @Override\n" +
            "            public void onResponse(#c:type# data) {\n" +
            "                notify#c:name#Listeners(data);\n" +
            "            }\n" +
            "\n" +
            "            @Override\n" +
            "            public void onException(ApplicationException ex) {\n" +
            "            }\n" +
            "        });\n" +
            "        RequestHandler.execute(r);\n" +
            "    }\n";
    public static final String mMethodNotify = "    private void notify#c:name#Listeners(#c:type# data){\n" +
            "        for (#c:name#LoadingListener l: m#c:name#Listeners) {\n" +
            "            l.on#c:name#Loaded(data);\n" +
            "        }\n" +
            "    }\n";

    public static final String mImportBody = "\nimport #c:model.path#.model.#c:name.l#.#c:fname.l#.#c:name#Request;\n" +
            "import #c:model.path#.model.#c:name.l#.#c:fname.l#.#c:name#LoadingListener;";
}
