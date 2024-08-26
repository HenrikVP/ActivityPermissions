package dk.tec.activitypermissions;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        openSecondActivity();
    }

    void openSecondActivity(){
        EditText mail = findViewById(R.id.et_mail);
        EditText subject = findViewById(R.id.et_subject);
        EditText message = findViewById(R.id.et_message);
        Button button = findViewById(R.id.btn_secondActivity);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //EMAIL
                Mail email = new Mail();
                email.setEmail(mail.getText().toString());
                email.setSubject(subject.getText().toString());
                email.setContent(message.getText().toString());

//                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
//                intent.putExtra("mailObject", email);
//                startActivity(intent);

                String[] array = new String[]{email.getEmail()};

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, array);
                intent.putExtra(Intent.EXTRA_SUBJECT, email.getSubject());
                intent.putExtra(Intent.EXTRA_TEXT, email.getContent());
//                startActivity(intent);

                //WEBSITE IN BROWSER
                Intent intent2 = new Intent(Intent.ACTION_VIEW);
                intent2.setData(Uri.parse("https://google.com"));
//                startActivity(intent2);

                //CALENDAR
                Calendar cal = Calendar.getInstance();
                Intent intent3 = new Intent(Intent.ACTION_EDIT);
                intent3.setType("vnd.android.cursor.item/event");
                intent3.putExtra("beginTime", cal.getTimeInMillis());
                cal.add(Calendar.MINUTE, 20);
                intent3.putExtra("endTime", cal.getTimeInMillis());
                intent3.putExtra("title", "Frokostpause");
                intent3.putExtra("allDay", false);
                startActivity(intent3);
            }
        });
    }
}