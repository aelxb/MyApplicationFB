import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import ru.konder.myapplicationfb.R

class MainActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()

        loginBtn.setOnClickListener {
            loginUsers()
        }

        signUpBtn.setOnClickListener {
            signUpUsers()
        }

    }

    private fun signUpUsers() {
        auth.createUserWithEmailAndPassword(email_login.text.toString(), password_login.text.toString())
                .addOnCompleteListener(this) { task ->
                    if(task.isSuccessful){
                        Toast.makeText(baseContext, "Вы зарегистрированы :)", Toast.LENGTH_LONG).show()
                    } else{
                        Toast.makeText(baseContext,task.exception.toString(),
                                Toast.LENGTH_LONG).show()
                        Log.i("SignUpUsers", task.exception.toString())
                    }
                }
    }

    private fun loginUsers() {
        auth.signInWithEmailAndPassword(email_login.text.toString(),password_login.text.toString())
                .addOnCompleteListener(this){task ->
                    if (task.isSuccessful){
                        Toast.makeText(baseContext, "Добро пожаловать :3", Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(baseContext, "Ошибка при вводе данных, повторите снова", Toast.LENGTH_LONG).show()
                    }
                }
    }
}