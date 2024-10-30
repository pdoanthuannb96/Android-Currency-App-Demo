package id20210822.class154058.currency

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var etAmount: EditText
    private lateinit var spinnerSource: Spinner
    private lateinit var spinnerTarget: Spinner
    private lateinit var tvConvertedAmount: TextView
    private lateinit var btnConvert: Button

    private val exchangeRates = mapOf(
        "USD" to 1.0,        // Đô la Mỹ
        "EUR" to 0.85,       // Euro
        "JPY" to 110.0,      // Yên Nhật
        "GBP" to 0.74,       // Bảng Anh
        "AUD" to 1.35,       // Đô la Úc
        "CAD" to 1.25,       // Đô la Canada
        "CHF" to 0.91,       // Franc Thụy Sĩ
        "CNY" to 6.45,       // Nhân dân tệ Trung Quốc
        "SEK" to 8.7,        // Krona Thụy Điển
        "NZD" to 1.43,       // Đô la New Zealand
        "MXN" to 20.0,       // Peso Mexico
        "SGD" to 1.36,       // Đô la Singapore
        "HKD" to 7.78,       // Đô la Hong Kong
        "NOK" to 8.9,        // Krone Na Uy
        "KRW" to 1180.0,     // Won Hàn Quốc
        "TRY" to 8.6,        // Lira Thổ Nhĩ Kỳ
        "RUB" to 74.0,       // Rúp Nga
        "INR" to 73.5,       // Rupee Ấn Độ
        "BRL" to 5.2,        // Real Brazil
        "ZAR" to 14.7,       // Rand Nam Phi
        "VND" to 23175.0     // Việt Nam Đồng
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        etAmount = findViewById(R.id.etAmount)
        spinnerSource = findViewById(R.id.spinnerSource)
        spinnerTarget = findViewById(R.id.spinnerTarget)
        tvConvertedAmount = findViewById(R.id.tvConvertedAmount)
        btnConvert = findViewById(R.id.btnConvert)

        // setup Adapter
        val currencyList = exchangeRates.keys.toList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencyList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSource.adapter = adapter
        spinnerTarget.adapter = adapter

        btnConvert.setOnClickListener {
            convertCurrency()
        }

    }

    private fun convertCurrency() {
        val sourceCurrency = spinnerSource.selectedItem.toString()
        val targetCurrency = spinnerTarget.selectedItem.toString()
        val amount = etAmount.text.toString().toDoubleOrNull() ?: return

        val sourceRate = exchangeRates[sourceCurrency] ?: return
        val targetRate = exchangeRates[targetCurrency] ?: return
        val convertedAmount = (amount / sourceRate) * targetRate

        tvConvertedAmount.text = "%.2f".format(convertedAmount)
    }
}