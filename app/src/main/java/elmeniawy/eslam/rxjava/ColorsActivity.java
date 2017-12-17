package elmeniawy.eslam.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class ColorsActivity extends AppCompatActivity {
    @BindView(R.id.colorList)
    RecyclerView colorListView;

    SimpleStringAdapter simpleStringAdapter;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);
        configureLayout();
        createObservable();
    }

    private void createObservable() {
        Observable<List<String>> listObservable = Observable.just(getColorList());
        disposable = listObservable.subscribe(colors -> simpleStringAdapter.setStrings(colors));

    }

    private void configureLayout() {
        ButterKnife.bind(this);
        colorListView.setLayoutManager(new LinearLayoutManager(this));
        simpleStringAdapter = new SimpleStringAdapter(this);
        colorListView.setAdapter(simpleStringAdapter);
    }

    private static List<String> getColorList() {
        ArrayList<String> colors = new ArrayList<>();
        colors.add("red");
        colors.add("green");
        colors.add("blue");
        colors.add("pink");
        colors.add("brown");
        return colors;
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
