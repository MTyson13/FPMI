package layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.martinbachvarov.fpmi.R;


public class MenuPageFragment extends Fragment {
    private TextView nameView;
    private static final String HI_MESSAGE = "Hi";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_page, container, false);
        nameView = (TextView) view.findViewById(R.id.nameView);

        setNameView();
        return view;
    }

    private void setNameView() {
        String value = getArguments().getString("username");
        nameView.setText(HI_MESSAGE + ", " + value);
    }
}
