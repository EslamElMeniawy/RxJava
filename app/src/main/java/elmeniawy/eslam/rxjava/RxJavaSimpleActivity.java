package elmeniawy.eslam.rxjava;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RxJavaSimpleActivity extends AppCompatActivity {
    @BindView(R.id.textViewResult)
    TextView resultView;

    //RecyclerView colorListView;
    //SimpleStringAdapter simpleStringAdapter;
    CompositeDisposable disposable = new CompositeDisposable();
    public int value = 0;

    final Observable<Integer> serverDownloadObservable = Observable.create(emitter -> {
        SystemClock.sleep(10000);
        emitter.onNext(5);
        emitter.onComplete();
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_simple);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonServer)
    void getServerData(Button serverButton) {
        serverButton.setEnabled(false);

        Disposable subscribe = serverDownloadObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(integer -> {
                    updateTheUserInterface(integer);
                    serverButton.setEnabled(true);
                });
    }

    @OnClick(R.id.buttonToast)
    void makeToast() {
        Toast.makeText(this, "Still active " + value++, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    private void updateTheUserInterface(int integer) {
        resultView.setText(String.valueOf(integer));
    }
}
