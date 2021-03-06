package sabinabaghiu.plannerzen.ui.lists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import sabinabaghiu.plannerzen.R;


public class HabitsFragment extends Fragment {

    private HabitsViewModel habitsViewModel;
    private RecyclerView habitRecyclerView;
    private TextView habitTextView;
    private HabitListsAdapter habitListsAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        habitsViewModel =
                new ViewModelProvider(this).get(HabitsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_habits, container, false);
        habitsViewModel.init();
        BottomNavigationView navigationView = getActivity().findViewById(R.id.nav_view);
        if (navigationView != null)
            navigationView.setVisibility(View.VISIBLE);


            //habit recycler view
        habitRecyclerView = root.findViewById(R.id.recyclerViewHabitMyLists);
        habitRecyclerView.setHasFixedSize(true);
        habitTextView = root.findViewById(R.id.textViewNoHabitsLists);
        habitRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        habitListsAdapter = new HabitListsAdapter(getContext());
        habitRecyclerView.setAdapter(habitListsAdapter);
        habitsViewModel.getHabits().observe(getViewLifecycleOwner(), habits -> {
            if (habits.size() == 0) {
                habitRecyclerView.setVisibility(View.INVISIBLE);
                habitTextView.setVisibility(View.VISIBLE);
            }
            else {
                habitRecyclerView.setVisibility(View.VISIBLE);
                habitTextView.setVisibility(View.INVISIBLE);
                habitListsAdapter.updateList(habits);
            }
        });


             //add button
        CoordinatorLayout coordinatorLayout = root.findViewById(R.id.coordinatorLayoutHabits);
        FloatingActionButton fab = root.findViewById(R.id.fabHabits);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.navigation_add_habit);
            }
        });

            //swiping for edit and delete
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeItemTouchHelperHabits(habitListsAdapter));
        itemTouchHelper.attachToRecyclerView(habitRecyclerView);

        return root;
        }
}
