package com.example.danielmezam;

import android.content.Context;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class medicamentosListaAdaptable extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<medicamentosShow> medicamentosList;

    public medicamentosListaAdaptable(Context context, int layout, ArrayList<medicamentosShow> medicamentosList) {
        this.context = context;
        this.layout = layout;
        this.medicamentosList = medicamentosList;
    }

    @Override
    public int getCount() {
        return medicamentosList.size();
    }

    @Override
    public Object getItem(int position) {
        return medicamentosList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        TextView txtMedicamento;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);
            holder.txtMedicamento = (TextView) row.findViewById(R.id.textView );
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        medicamentosShow medi = medicamentosList.get(position);

        holder.txtMedicamento.setText(medi.getNombreMedicamento());

        return null;
    }
}
