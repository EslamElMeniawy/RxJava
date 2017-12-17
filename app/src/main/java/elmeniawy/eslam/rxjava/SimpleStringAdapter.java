package elmeniawy.eslam.rxjava;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * SimpleStringAdapter
 * <p>
 * Created by Eslam El-Meniawy on 17-Dec-2017.
 * CITC - Mansoura University
 */

public class SimpleStringAdapter extends RecyclerView.Adapter<SimpleStringAdapter.ViewHolder> {
    private final Context mContext;
    private final List<String> mStrings = new ArrayList<>();

    public SimpleStringAdapter(Context context) {
        mContext = context;
    }

    public void setStrings(List<String> newStrings) {
        mStrings.clear();
        mStrings.addAll(newStrings);
        notifyDataSetChanged();
    }

    @Override
    public SimpleStringAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.string_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleStringAdapter.ViewHolder holder, int position) {
        holder.colorTextView.setText(mStrings.get(position));
        holder.itemView.setOnClickListener(v -> Toast.makeText(mContext, mStrings.get(position), Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return mStrings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewColorDisplay)
        TextView colorTextView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
