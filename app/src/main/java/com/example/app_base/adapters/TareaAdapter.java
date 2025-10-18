package com.example.app_base.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.app_base.R;
import models.DBHelper;
import models.Tarea;

import java.util.List;

public class TareaAdapter extends RecyclerView.Adapter<TareaAdapter.TareaViewHolder> {

    private List<Tarea> listaTareas;
    private Context context;
    private DBHelper dbHelper;
    private OnTareaClickListener listener;

    // Interfaz para notificar cambios
    public interface OnTareaClickListener {
        void onTareaClick(Tarea tarea);
    }

    // Constructor con listener opcional
    public TareaAdapter(Context context, List<Tarea> listaTareas, OnTareaClickListener listener) {
        this.context = context;
        this.listaTareas = listaTareas;
        this.listener = listener;
        this.dbHelper = new DBHelper(context);
    }

    @Override
    public TareaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tarea, parent, false);
        return new TareaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TareaViewHolder holder, int position) {
        Tarea tarea = listaTareas.get(position);
        boolean completada = tarea.getEstado().equals("completada");

        // Evitar que el listener se dispare al reciclar vistas
        holder.checkEstado.setOnCheckedChangeListener(null);
        holder.checkEstado.setChecked(completada);

        // Visuales
        holder.itemView.setAlpha(completada ? 0.5f : 1.0f);
        holder.txtTitulo.setPaintFlags(completada ? Paint.STRIKE_THRU_TEXT_FLAG : 0);

        // Mostrar datos
        holder.txtTitulo.setText(tarea.getTitulo());
        holder.txtFecha.setText(tarea.getFecha());
        holder.txtPrioridad.setText("Prioridad: " + tarea.getPrioridad());

        // Listener para marcar como completada
        holder.checkEstado.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String nuevoEstado = isChecked ? "completada" : "pendiente";
            dbHelper.actualizarEstadoTarea(tarea.getId(), nuevoEstado);
            tarea.setEstado(nuevoEstado);

            if (listener != null) {
                listener.onTareaClick(tarea); // Notifica a la actividad
            } else {
                notifyItemChanged(position); // Solo actualiza visual si no hay listener
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaTareas.size();
    }

    public static class TareaViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitulo, txtFecha, txtPrioridad;
        CheckBox checkEstado;

        public TareaViewHolder(View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            txtFecha = itemView.findViewById(R.id.txtFecha);
            txtPrioridad = itemView.findViewById(R.id.txtPrioridad);
            checkEstado = itemView.findViewById(R.id.checkEstado);
        }
    }
}