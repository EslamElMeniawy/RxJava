package elmeniawy.eslam.rxjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonFirst)
    void firstButtonClicked() {
        startActivity(new Intent(this, RxJavaSimpleActivity.class));
    }

    @OnClick(R.id.buttonSecond)
    void secondButtonClicked() {
        startActivity(new Intent(this, ColorsActivity.class));
    }

    @OnClick(R.id.buttonThird)
    void thirdButtonClicked() {
        startActivity(new Intent(this, BooksActivity.class));
    }
}
