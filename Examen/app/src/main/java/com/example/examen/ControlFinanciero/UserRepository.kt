import android.content.ContentValues

class UserRepository(private val dbHelper: FinanceDbHelper) {

    fun createUser(username: String, password: String): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("username", username)
            put("password", password)
        }
        return db.insert("users", null, values)
    }

    fun login(username: String, password: String): Int? {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT id FROM users WHERE username = ? AND password = ?",
            arrayOf(username, password)
        )

        cursor.use {
            return if (it.moveToFirst()) {
                it.getInt(it.getColumnIndexOrThrow("id"))
            } else {
                null
            }
        }
    }
}
