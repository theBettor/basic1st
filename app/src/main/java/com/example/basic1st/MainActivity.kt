package com.example.basic1st // 이게 없어서 추가해줬고 R 에러로 import해줬던 이상한 것도 지워버림

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.basic1st.databinding.ActivityMainBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var job: Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view) // 위 코드처럼, view라는 변수를 선언해서 setContentView에 view만 넣게하는 방법도 있구나..!
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupButton()
        setRandomValueBetweenOneToHundred()
        setJobAndLaunch()
    }

    private fun setupButton() {
        // TODO("activity_main.xml에 작성되어 있는 button을 findViewById를 사용하여 button이라는 Button 타입의 변수에 할당하기)


        // 위 코드가 작성되어야 아래 코드가 수행될 수 있음!
        binding.clickButton.setOnClickListener {
            job?.cancel()
            checkAnswerAndShowToast()
        }
    }

    private fun setRandomValueBetweenOneToHundred() {
        // TODO("activity_main.xml에 작성되어 있는 textViewRandom을 findViewById를 사용하여 randomTextView라는 TextView 타입의 변수에 할당하기)

        // TODO("Kotlin Random을 찾아보고 1이상 100이하의 숫자를 랜덤으로 구하여 Int 타입의 randomValue라는 변수에 할당하는 코드 작성하기")
//        val randomValue = Random.nextInt(1, 101)
        val randomValue = (1..100).random()
        // TODO("위 random으로 구한 값을 randomTextView에 세팅하여 화면에 보여주도록 하기")
        binding.textViewRandom.text = randomValue.toString()
    }

    private fun setJobAndLaunch() {
        // TODO("activity_main.xml에 작성되어 있는 spartaTextView를 findViewById를 사용하여 textView라는 TextView 타입의 변수에 할당하기)

        /*job = lifecycleScope.launch {
            var i = 1
            while (isActive && i <= 100) {
                textView.text = i.toString()
                delay(500)
                i += 1 // ++i, i++
            }
        }*/

        job = lifecycleScope.launch {
            // TODO("위 주석처리된 while 루프 코드를 참조하여 for 루프로 작성해보기(1이상 100이하)")
            // 아래 /*...*/ 안에 코드를 작성하세요.
            for (i in 1..100) {
                if (isActive) {
                    binding.spartaTextView.text = i.toString() // 여기 text에 갖다대면 getText가 숨겨져 있는데 코틀린은 앞에 수식어를 붙히지 않아도 된다고 함.
                    delay(500)
                }
            }
        }
    }


    private fun checkAnswerAndShowToast() {
        // TODO("activity_main.xml에 작성되어 있는 spartaTextView를 findViewById를 사용하여 textView라는 TextView 타입의 변수에 할당하기)
        val spartText = binding.spartaTextView.text.toString()

        // TODO("activity_main.xml에 작성되어 있는 textViewRandom을 findViewById를 사용하여 randomTextView라는 TextView 타입의 변수에 할당하기)
        val randomText = binding.textViewRandom.text.toString()

        // TODO("if문 사용해보기 - 위에서 2개의 변수를 작성완료하고 나면 textView의 값과 randomTextView의 값이 같은지 다른지를 확인하여 Toast 띄우기")
        if (spartText == randomText) {
            Toast.makeText(this, "같다", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "틀립니다", Toast.LENGTH_SHORT).show()
        }

        /**
         * Toast 사용 예)
         * Toast.makeText(this, "메세지", Toast.LENGTH_SHORT).show() // Toast.LENGTH_SHORT 대신 Toast.LENGTH_LONG 또한 사용 가능
         */
    }
}