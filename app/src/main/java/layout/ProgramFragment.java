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
    private static final String FIRST_COURSE_URL = "http://www.tu-sofia.bg/weeklyprogram/2016-2017-zimen/bakalavar/%D0%A4%D0%9F%D0%9C%D0%98-potok-14-%D0%9F%D0%9C-kurs-1.pdf";
    private static final String SECOND_COURSE_URL = "http://www.tu-sofia.bg/weeklyprogram/2016-2017-zimen/bakalavar/%D0%A4%D0%9F%D0%9C%D0%98-potok-16-kurs-2.pdf";
    private static final String THIRD_COURSE_URL = "http://www.tu-sofia.bg/weeklyprogram/2016-2017-zimen/bakalavar/%D0%A4%D0%9F%D0%9C%D0%98-potok---kurs-3.pdf";
    private static final String FOURTH_COURSE_URL = "http://www.tu-sofia.bg/weeklyprogram/2016-2017-zimen/bakalavar/%D0%A4%D0%9F%D0%9C%D0%98-potok---kurs-4.pdf";
    private String pdf;
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

    private String setCorrectPdf() {
        String course = getArguments().getString("course");
        if (course.equals("I Course")) {
            pdf = FIRST_COURSE_URL;
        } else if (course.equals("II Course")) {
            pdf = SECOND_COURSE_URL;
        } else if (course.equals("III Course")) {
            pdf = THIRD_COURSE_URL;
        } else if (course.equals("IV Course")) {
            pdf = FOURTH_COURSE_URL;
        }
        return pdf;
    }

}
