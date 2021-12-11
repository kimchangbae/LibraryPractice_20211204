package com.example.librarypractice_20211204

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupEvents()
        setValues()
    }

    private fun setupEvents() {
        circleImageView.setOnClickListener {
            val myIntent = Intent(this, ViewPhotoActivity::class.java)
            startActivity(myIntent)
        }

        btnCall.setOnClickListener {
            val pl = object : PermissionListener {
                override fun onPermissionGranted() {
                    val myUri = Uri.parse("tel:010-5555-9999")
                    val myIntent = Intent(Intent.ACTION_CALL, myUri)
                    startActivity(myIntent)
                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                    Toast.makeText(this@MainActivity, "권한이 없어 전화 연결 불가", Toast.LENGTH_SHORT).show()
                }
            }
            TedPermission.create()
                .setPermissionListener(pl)
                .setPermissions(
                    Manifest.permission.CALL_PHONE
                    , Manifest.permission.ACCESS_MEDIA_LOCATION)
                .check()
        }
    }

    private fun setValues() {
        // 인터넷 상에 있는 이미지를 -> 곧바로 이미지뷰에 적용.
        // https://www.theguru.co.kr/data/photos/20210937/art_16316071303022_bf8378.jpg

        // 이미지 주소를 변수 에 담아두자.
        val imageURL = "https://www.theguru.co.kr/data/photos/20210937/art_16316071303022_bf8378.jpg"
        Glide
            .with( this )
            .load( imageURL )
            .into( imgInternet )
    }
}