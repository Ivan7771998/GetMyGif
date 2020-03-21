package com.example.getmygif;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.getmygif.adapters.MyAdapterList;
import com.example.getmygif.models.GifResponse;
import com.example.getmygif.network.GifEndpoint;
import com.example.getmygif.network.RetrofitClientInstance;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.ResourceSingleObserver;
import io.reactivex.schedulers.Schedulers;
import ru.alexbykov.nopaginate.callback.OnLoadMoreListener;
import ru.alexbykov.nopaginate.paginate.NoPaginate;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapterList adapter;
    private RecyclerView.LayoutManager layoutManager;
    private EditText idTextSearch;
    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        idTextSearch = findViewById(R.id.idTextSearch);
        btnSearch =findViewById(R.id.btn_search);
        idTextSearch.setText("coronavirus");
        testRequest(idTextSearch.getText().toString());
        btnSearch.setOnClickListener(v->{
            testRequest(idTextSearch.getText().toString());
        });

        //"BojackHorseman"
    }

    public void getResp(GifResponse gifResponse){
        adapter = new MyAdapterList(MainActivity.this, gifResponse.getData());
        recyclerView.setAdapter(adapter);
    }
    private void testRequest(String searchString) {
        GifEndpoint endpoint = RetrofitClientInstance.getRetrofitInstance().create(GifEndpoint.class);
        endpoint.search(getString(R.string.api_key), searchString,100)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceSingleObserver<GifResponse>() {
                    @Override
                    public void onSuccess(GifResponse gifResponse) {
                        getResp(gifResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });


    }
}
