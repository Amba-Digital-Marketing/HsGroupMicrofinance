package com.microfinance.hsmicrofinance.UI.Fragments.Transactions;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;


import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.microfinance.hsmicrofinance.BuildConfig;
import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.UI.adapters.LatestTransactionAdapter;
import com.microfinance.hsmicrofinance.databinding.FragmentBasicLatestTransactionsBinding;
import com.microfinance.hsmicrofinance.Network.models.TransactionHistory;
import com.microfinance.hsmicrofinance.UI.viewmodels.LatestTransactionViewModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import timber.log.Timber;


public class BasicLatestTransactions extends Fragment implements LatestTransactionAdapter.LatestTransactionInterface {
    private static final String TAG = "LATEST";
    FragmentBasicLatestTransactionsBinding mBinding;
    private RecyclerView mRecyclerView;
    private LatestTransactionAdapter mAdapter;
    private List<TransactionHistory.TransactionHistoryDetail> mLatestTransactions = new ArrayList<>();
    private LatestTransactionViewModel mLatestTransactionViewModel;
    private NavController mNavController;
    private SimpleDateFormat mSimpleDateFormat;
    private Date mStartDate;
    private Date mEndDate;
    private Date mTestDate;
    private List<TransactionHistory.TransactionHistoryDetail> mList;
    private LatestTransactionAdapter mLatestTransactionAdapter;
    private Calendar mCalendar;
    private String mFormat;
    private SimpleDateFormat mSdf;
    private DatePickerDialog.OnDateSetListener mDateStart,mDateEnd;

    public BasicLatestTransactions() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentBasicLatestTransactionsBinding.inflate(inflater,container,false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBinding.toolbar.setTitle("Transactions");
        mBinding.toolbar.setNavigationOnClickListener(v->mNavController.navigate(R.id.action_basicLatestTransactions2_to_basicDashboard));
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFormat = "yyyy-MM-dd";
        mSdf = new SimpleDateFormat(mFormat, Locale.US);
        mRecyclerView = mBinding.latestTransactionRecyclerview;

        mNavController = Navigation.findNavController(view);
        mBinding.progrebar.setVisibility(View.VISIBLE);

        mLatestTransactionViewModel = new ViewModelProvider(requireActivity()).get(LatestTransactionViewModel.class);
        mLatestTransactionViewModel.getLatestTransactionObserver().observe(getViewLifecycleOwner(), transactionHistoryDetails -> {
            mLatestTransactions = transactionHistoryDetails;
            //todo check if list is empty
            if(!mLatestTransactions.isEmpty()){
                mBinding.page.setVisibility(View.GONE);
            }

            Log.d(TAG, "onViewCreated: "+ mLatestTransactions.size());
            Collections.reverse(mLatestTransactions);
            Timber.tag("Transaction").d("trx:::: "+mLatestTransactions.toString());
            mAdapter = new LatestTransactionAdapter(requireActivity(),mLatestTransactions,this);
            mRecyclerView.setAdapter(mAdapter);
            mBinding.progrebar.setVisibility(View.GONE);
        });
        mLatestTransactionViewModel.makeAPIcall(getContext());
        mBinding.btnSearch.setOnClickListener(v -> {
            try {
                doSearch();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        mCalendar = Calendar.getInstance();
        mDateStart = (view1, year, month, dayOfMonth) -> {
            mCalendar.set(Calendar.YEAR,year);
            mCalendar.set(Calendar.MONTH,month);
            mCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            upStartDate();

        };
        mDateEnd = (view1, year, month, dayOfMonth) -> {
            mCalendar.set(Calendar.YEAR,year);
            mCalendar.set(Calendar.MONTH,month);
            mCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

            upEndDate();
        };
        mBinding.etStartdate.setOnClickListener(v -> new DatePickerDialog(getContext(), mDateStart, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),mCalendar.get(Calendar.DAY_OF_MONTH)).show());
        mBinding.tvEndDate.setOnClickListener(v -> new DatePickerDialog(getContext(), mDateEnd, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),mCalendar.get(Calendar.DAY_OF_MONTH)).show());
//        mFragmentBasicLatestTransactionsBinding.imDownload.setOnClickListener(v -> DownLoadPdf());

    }
    public void DownLoadPdf(){
        //First Check if the external storage is writable
//        String state = Environment.getExternalStorageState();
//        if(!Environment.MEDIA_MOUNTED.equals(state))
//            Toast.makeText(getContext(), "storage state check", Toast.LENGTH_SHORT).show();
//
//        //Create a directory for your PDF
        File pdfDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),"MyProject");
        if(!pdfDir.exists()){
            pdfDir.mkdir();
        }
        //Create the name of your PDF file
        File pdfFile = new File(pdfDir,"transactions.pdf");

        try{
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            document.open();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            getRecyclerViewScreenShot(mRecyclerView).compress(Bitmap.CompressFormat.PNG, 100,stream);
            byte[]bytes = stream.toByteArray();
            addImage(document,bytes);
            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        Intent intent =new Intent(Intent.ACTION_VIEW);
        Uri uri = FileProvider.getUriForFile(getContext(), BuildConfig.APPLICATION_ID + ".provider",pdfFile);
        intent.setDataAndType(uri, "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);

    }
    private static void addImage(Document document, byte[] byteArray)
    {
        Image image = null;
        try
        {
            image = Image.getInstance(byteArray);
        }
        catch (BadElementException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (MalformedURLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // image.scaleAbsolute(150f, 150f);
        try
        {
            document.add(image);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static Bitmap getRecyclerViewScreenShot(RecyclerView view) {
        RecyclerView.Adapter adapter = view.getAdapter();
        Bitmap bigBitmap = null;
        if (adapter != null) {
            int size = adapter.getItemCount();
            int height = 0;
            Paint paint = new Paint();
            int iHeight = 0;
            final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

            // Use 1/8th of the available memory for this memory cache.
            final int cacheSize = maxMemory / 8;
            LruCache<String, Bitmap> bitmaCache = new LruCache<>(cacheSize);
            for (int i = 0; i < size; i++) {
                RecyclerView.ViewHolder holder = adapter.createViewHolder(view, adapter.getItemViewType(i));
                adapter.onBindViewHolder(holder, i);
                holder.itemView.measure(View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.EXACTLY),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                holder.itemView.layout(0, 0, holder.itemView.getMeasuredWidth(), holder.itemView.getMeasuredHeight());
                holder.itemView.setDrawingCacheEnabled(true);
                holder.itemView.buildDrawingCache();
                Bitmap drawingCache = holder.itemView.getDrawingCache();
                if (drawingCache != null) {

                    bitmaCache.put(String.valueOf(i), drawingCache);
                }
//                holder.itemView.setDrawingCacheEnabled(false);
//                holder.itemView.destroyDrawingCache();
                height += holder.itemView.getMeasuredHeight();
            }

            bigBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), height, Bitmap.Config.ARGB_8888);
            Canvas bigCanvas = new Canvas(bigBitmap);
            bigCanvas.drawColor(Color.WHITE);

            for (int i = 0; i < size; i++) {
                Bitmap bitmap = bitmaCache.get(String.valueOf(i));
                bigCanvas.drawBitmap(bitmap, 0f, iHeight, paint);
                iHeight += bitmap.getHeight();
                bitmap.recycle();
            }

        }
        return bigBitmap;
//        int size = recyclerView.getAdapter().getItemCount();
//        RecyclerView.ViewHolder holder = recyclerView.getAdapter().createViewHolder(recyclerView,0);
//        recyclerView.getAdapter().onBindViewHolder(holder,0);
//        holder.itemView.measure(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), View.MeasureSpec.EXACTLY),View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//        holder.itemView.layout(0, 0, holder.itemView.getMeasuredWidth(), holder.itemView.getMeasuredHeight());
//        Bitmap bigBitmap = Bitmap.createBitmap(recyclerView.getMeasuredWidth(), holder.itemView.getMeasuredHeight() * size,
//                Bitmap.Config.ARGB_8888);
//        Canvas bigCanvas = new Canvas(bigBitmap);
//        bigCanvas.drawColor(Color.WHITE);
//        Paint paint = new Paint();
//        int iHeight = 0;
//        holder.itemView.setDrawingCacheEnabled(true);
//        holder.itemView.buildDrawingCache();
//        bigCanvas.drawBitmap(holder.itemView.getDrawingCache(), 0f, iHeight, paint);
//        holder.itemView.setDrawingCacheEnabled(false);
//        holder.itemView.destroyDrawingCache();
//        iHeight += holder.itemView.getMeasuredHeight();
//        for (int i = 1; i < size; i++) {
//            recyclerView.getAdapter().onBindViewHolder(holder, i);
//            holder.itemView.setDrawingCacheEnabled(true);
//            holder.itemView.buildDrawingCache();
//            bigCanvas.drawBitmap(holder.itemView.getDrawingCache(), 0f, iHeight, paint);
//            iHeight += holder.itemView.getMeasuredHeight();
//            holder.itemView.setDrawingCacheEnabled(false);
//            holder.itemView.destroyDrawingCache();
//        }
//        return bigBitmap;


    }

    private void upStartDate() {

        mBinding.etStartdate.setText(mSdf.format(mCalendar.getTime()));

    }
    private void upEndDate() {

        mBinding.tvEndDate.setText(mSdf.format(mCalendar.getTime()));

    }


    private void doSearch() throws ParseException {
        mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        mStartDate = mSimpleDateFormat.parse(mBinding.etStartdate.getText().toString().trim());
        mEndDate = mSimpleDateFormat.parse(mBinding.tvEndDate.getText().toString().trim());
        mList = new ArrayList<>();
        for(int i = 0; i<mLatestTransactions.size(); i++){
            mTestDate = mSimpleDateFormat.parse(mLatestTransactions.get(i).getCreatedAt().substring(0,10));

            if((mTestDate.after(mStartDate) || mTestDate.equals(mStartDate))  &&  (mTestDate.before(mEndDate) || mTestDate.equals(mEndDate))){
               mList.add(mLatestTransactions.get(i));
            }
        }
        Timber.tag("date").d("dates %s", mList.toString());
        Timber.tag("date").d("size %s", mList.size());
        try {
            mLatestTransactionAdapter = new LatestTransactionAdapter(requireActivity(),mList,this);
            mRecyclerView.setAdapter(mLatestTransactionAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }



    }


    @Override
    public void onClickingLatestTrx(View v, int position) {
        TransactionHistory.TransactionHistoryDetail detail = mAdapter.getTransactionHistoryDetail(position);
        Bundle args = new Bundle();
        args.putString("trxId", detail.getTrxid());
        args.putDouble("amount", detail.getAmount());
        args.putDouble("balance",detail.getBalance());
        args.putDouble("fee", detail.getFee());
        args.putString("type", detail.getType());
        args.putInt("status", detail.getStatus());
        args.putString("date",detail.getCreatedAt());
        args.putString("info",detail.getInfo());

        mNavController.navigate(R.id.action_basicLatestTransactions2_to_singleTransaction, args);
    }
}