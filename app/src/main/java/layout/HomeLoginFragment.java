package layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.martinbachvarov.fpmi.R;

public class HomeLoginFragment extends Fragment {
    private Button btnContinue;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_login_fragment, container, false);

        btnContinue = (Button) view.findViewById(R.id.buttonHomeContinue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment();
            }
        });

        return view;
    }

    private void openFragment() {
        FragmentTransaction fTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fTransaction.replace(R.id.container, new MenuPageFragment());
        fTransaction.commit();
    }
}
