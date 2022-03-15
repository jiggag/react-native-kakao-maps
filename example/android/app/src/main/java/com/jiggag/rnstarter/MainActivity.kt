package com.jiggag.rnstarter

import android.os.Bundle
import com.facebook.react.ReactActivity

class MainActivity : ReactActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun getMainComponentName(): String {
    return "RNStarter"
  }
}