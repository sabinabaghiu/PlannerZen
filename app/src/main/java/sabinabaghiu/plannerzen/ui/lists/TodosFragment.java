package sabinabaghiu.plannerzen.ui.lists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import sabinabaghiu.plannerzen.R;

public class TodosFragment extends Fragment {


    private ListsViewModel listsViewModel;
    private RecyclerView listRecyclerView;
    private TextView listTextView;
    TaskListAdapter taskListAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listsViewModel =
                new ViewModelProvider(this).get(ListsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_todos, container, false);

                //todo recycler view
        listRecyclerView = root.findViewById(R.id.recyclerViewTodoMyLists);
        listTextView = root.findViewById(R.id.textViewNoTodoLists);
        listRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        listsViewModel.getAllTodos().observe(getViewLifecycleOwner(), todos -> {
//            if (!todos.isEmpty()) {
//                for (Todo t : todos) {
//                    listRecyclerView.setVisibility(View.VISIBLE);
//                    todoListsAdapter = new TodoListsAdapter((ArrayList<Todo>) todos);
//                    listRecyclerView.setAdapter(todoListsAdapter);
//                    listTextView.setVisibility(View.GONE);
//                }
//            } else {
//                listRecyclerView.setVisibility(View.GONE);
//                listTextView.setVisibility(View.VISIBLE);
//            }
//        });

            //add button
        CoordinatorLayout coordinatorLayout = root.findViewById(R.id.coordinatorLayoutTodos);
        FloatingActionButton fab = root.findViewById(R.id.fabTodos);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.navigation_todo_list_add);
            }
        });

        return root;
    }
}
