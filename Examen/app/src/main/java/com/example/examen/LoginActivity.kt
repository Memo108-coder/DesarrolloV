import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var userRepo: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login) // luego creamos este XML

        val dbHelper = FinanceDbHelper(this)
        userRepo = UserRepository(dbHelper)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val edtUser = findViewById<EditText>(R.id.edtUsername)
        val edtPass = findViewById<EditText>(R.id.edtPassword)

        btnLogin.setOnClickListener {
            val user = edtUser.text.toString()
            val pass = edtPass.text.toString()

            val userId = userRepo.login(user, pass)
            if (userId != null) {
                // Aquí más adelante vas a ir al MainActivity
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
