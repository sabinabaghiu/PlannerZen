package sabinabaghiu.plannerzen.ui.lists;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import sabinabaghiu.plannerzen.R;
import sabinabaghiu.plannerzen.ui.today.Habit;

public class AddHabitFragment extends Fragment {
    private ListsViewModel listsViewModel;
    Spinner spinner;
    private EditText titleEditText;
    private EditText goalEditText;
    private Button saveButton;
    private Button cancelButton;
    private DatabaseReference reference;
    Habit habit;
    int iconId;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listsViewModel =
                new ViewModelProvider(this).get(ListsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_habit, container, false);
        listsViewModel.init();
        titleEditText = root.findViewById(R.id.add_habit_title);
        goalEditText = root.findViewById(R.id.add_habit_goal);
         spinner = root.findViewById(R.id.spinner_habit_icon);
        spinner.setAdapter(new SpinnerAdapter(getContext(), R.layout.spinner_row, getAllRows()));
        saveButton = root.findViewById(R.id.button_save_habit);
        cancelButton = root.findViewById(R.id.button_cancel_habit);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://plannerzen-43809-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = database.getReference().child("Habits");

        saveButton.setOnClickListener(v -> {
            String title = titleEditText.getText().toString().trim();
            int goal = Integer.parseInt(goalEditText.getText().toString().trim());
//            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    iconId = getAllRows().get(position).getIconId();
//                    Toast.makeText(getContext(), "Selected: " + getAllRows().get(position).getText(), Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//                }
//            });

            habit = new Habit(title, goal, R.drawable.icon_sun, false, 0);
            reference.push().setValue(habit);
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.navigation_habits);
        });

        cancelButton.setOnClickListener(v -> {
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.navigation_add_habit);
        });

        return root;
    }


    public List<SpinnerRow> getAllRows(){
        List<SpinnerRow> options = new ArrayList<>();
        options.add(new SpinnerRow("Book icon", R.drawable.icon_book));
        options.add(new SpinnerRow("Sport icon", R.drawable.icon_sport));
        options.add(new SpinnerRow("Sun icon", R.drawable.icon_sun));
        return options;
    }
}
