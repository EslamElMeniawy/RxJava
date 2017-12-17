package elmeniawy.eslam.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BooksActivity extends AppCompatActivity {
    @BindView(R.id.booksList)
    RecyclerView booksRecyclerView;
    @BindView(R.id.progressBarLoading)
    ProgressBar progressBar;

    private Disposable bookSubscription;
    private SimpleStringAdapter stringAdapter;
    private RestClient restClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        restClient = new RestClient(this);
        configureLayout();
        createObservable();
    }

    private void createObservable() {
        Observable<List<String>> booksObservable =
                Observable.fromCallable(() -> restClient.getFavoriteBooks());

        bookSubscription = booksObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::displayBooks);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (bookSubscription != null && !bookSubscription.isDisposed()) {
            bookSubscription.dispose();
        }
    }

    private void displayBooks(List<String> books) {
        stringAdapter.setStrings(books);
        progressBar.setVisibility(View.GONE);
        booksRecyclerView.setVisibility(View.VISIBLE);
    }

    private void configureLayout() {
        ButterKnife.bind(this);
        booksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        stringAdapter = new SimpleStringAdapter(this);
        booksRecyclerView.setAdapter(stringAdapter);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (bookSubscription != null && !bookSubscription.isDisposed()) {
            bookSubscription.dispose();
        }
    }
}
