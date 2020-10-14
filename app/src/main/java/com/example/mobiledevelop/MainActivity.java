package com.example.mobiledevelop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<User> users = new ArrayList<>();
    Context context;
    LayoutInflater layoutInflater;
    FrameLayout UserPanel;
    TextView NameTextView, StateTextView, AgeTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i = 0; i < 10; i++){ users.add(new User("User" + i,"State" + i, i+10, new Random().nextInt(3))); }
        initList();
    }
    private void initList() {
        recyclerView = findViewById(R.id.rv);
        context = this;
        layoutInflater = LayoutInflater.from(context);
        UserListAdapter userListAdapter = new UserListAdapter();
        recyclerView.setAdapter(userListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        UserPanel = findViewById(R.id.userPanel);
            NameTextView = findViewById(R.id.NameTextView);
            StateTextView = findViewById(R.id.StateTextView);
            AgeTextView = findViewById(R.id.AgeTextView);
    }

    public void BackToList(View view) {
        UserVisibility(false);
    }

    private void UserVisibility(boolean visible) {
        if(visible)
            UserPanel.setVisibility(View.VISIBLE);
        else
            UserPanel.setVisibility(View.GONE);
    }

    public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
        @NonNull
        @Override
        public UserListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout.item_user, parent, false);
            return new ViewHolder(view);
        }
        @Override
        public void onBindViewHolder(@NonNull UserListAdapter.ViewHolder holder, final int position) {
            User user = users.get(position);
            switch (user.getStateSignal())
            {
                case 0:
                    holder.StateRound.setBackgroundResource(R.drawable.back_offline);
                    break;
                case 1:
                    holder.StateRound.setBackgroundResource(R.drawable.back_online);
                    break;
                case 2:
                    holder.StateRound.setBackgroundResource(R.drawable.back_departed);
                    break;
            }
            holder.nameView.setText(user.getName());
            holder.stateView.setText(user.getState());
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    InitPanel(users.get(position));
                    UserVisibility(true);
                }
            });
        }
        @Override
        public int getItemCount() {
            return users.size();
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            final TextView nameView, stateView;
            final View view;
            final FrameLayout StateRound;
            ViewHolder(View view){
                super(view);
                StateRound = view.findViewById(R.id.StateRound);
                nameView = (TextView) view.findViewById(R.id.Name);
                stateView = (TextView) view.findViewById(R.id.State);
                this.view = view;
            }
        }
    }

    private void InitPanel(User user) {
        NameTextView.setText(user.getName());
        StateTextView.setText(user.getState());
        AgeTextView.setText(String.valueOf(user.getAge()));
    }
}
