package com.example.test

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.test.databinding.ActivityMainBinding
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.test.ui.screens.AuthScreen
import com.example.test.ui.screens.RoleSelectionScreen
import com.example.test.ui.screens.SplashScreen
import com.example.test.ui.theme.TestTheme
import com.example.test.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }

        setContent {
            TestTheme {
                var showSplash by remember { mutableStateOf(true) }
                val authState by viewModel.authState.collectAsState()
                
                AnimatedVisibility(
                    visible = showSplash,
                    exit = fadeOut()
                ) {
                    SplashScreen {
                        showSplash = false
                    }
                }
                
                AnimatedVisibility(
                    visible = !showSplash,
                    enter = fadeIn()
                ) {
                    when {
                        authState.isFirstTime -> RoleSelectionScreen(
                            onRoleSelected = viewModel::setUserRole
                        )
                        !authState.isAuthenticated -> AuthScreen(
                            userRole = authState.userRole!!,
                            onGoogleSignInClick = viewModel::signInWithGoogle,
                            onEmailSignInClick = viewModel::signInWithEmail
                        )
                        else -> MainContent()
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}