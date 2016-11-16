package layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.martinbachvarov.fpmi.R;

public class ProgramFragment extends Fragment {
    private WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_program, container, false);

        webView = (WebView) view.findViewById(R.id.programView);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + setCorrectPdf());

        return view;
    }

    private String setCorrectPdf(){
        String pdf = "";
        if ()
        return pdf;
    }

}
