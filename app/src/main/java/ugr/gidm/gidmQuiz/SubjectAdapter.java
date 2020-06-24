package ugr.gidm.gidmQuiz;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SubjectAdapter extends ArrayAdapter<SubjectItem> {

    static final String SUBJECT_COLOR = "SubjectColor";
    static final String SUBJECT_ID = "SubjectID";
    private Context mContext;
    private ArrayList<SubjectItem> mSubjectItems;

    SubjectAdapter(@NonNull Context context, int resource, @NonNull ArrayList<SubjectItem> subjectItems) {
        super(context, resource, subjectItems);
        this.mContext = context;
        this.mSubjectItems = subjectItems;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        SubjectViewHolder subjectViewHolder;
        final SubjectItem subjectItem = mSubjectItems.get(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.grid_view_item, parent, false);
            subjectViewHolder = new SubjectViewHolder(convertView);
            convertView.setTag(subjectViewHolder);
        }
        subjectViewHolder = (SubjectViewHolder) convertView.getTag();
        subjectViewHolder.subjectImage.setBackgroundColor(subjectItem.getmBgColor());
        subjectViewHolder.subjectTitle.setText(subjectItem.getmSubjectTitle());
        subjectViewHolder.subjectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent questionIntent = new Intent(mContext, QuestionActivity.class);
                questionIntent.putExtra(SUBJECT_ID, subjectItem.getmSubjectID());
                questionIntent.putExtra(SUBJECT_COLOR, subjectItem.getmBgColor());
                mContext.startActivity(questionIntent);
            }
        });
        return convertView;
    }

    static class SubjectViewHolder extends RecyclerView.ViewHolder{

        ImageView subjectImage;
        TextView subjectTitle;

        SubjectViewHolder(View itemView) {
            super(itemView);
            subjectImage = itemView.findViewById(R.id.subject_image);
            subjectTitle = itemView.findViewById(R.id.subject_title);
        }
    }
}
