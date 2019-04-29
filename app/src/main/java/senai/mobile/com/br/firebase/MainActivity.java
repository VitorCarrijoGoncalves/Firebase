package senai.mobile.com.br.firebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import senai.mobile.com.br.firebase.Classes.Usuario;
import senai.mobile.com.br.firebase.DAO.ConfiguracaoFirebase;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao;
    private EditText edtEmailLogin;
    private EditText edtSenhaLogin;
    private Button btnLogin;
    private Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtEmailLogin = (EditText) findViewById(R.id.edtEmail);
        edtSenhaLogin = (EditText) findViewById(R.id.edtESenha);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        if (usuarioLogado()) {

            Intent minhaConta = new Intent(MainActivity.this, CadastroUsuarioActivity.class);
            abrirNovaActivity(minhaConta);

        } else {

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!edtEmailLogin.getText().toString().equalsIgnoreCase("") && !edtSenhaLogin.getText().toString().equalsIgnoreCase("")) {

                        usuario = new Usuario();

                        usuario.setEmail(edtEmailLogin.getText().toString());
                        usuario.setSenha(edtSenhaLogin.getText().toString());

                        validarLogin();

                    } else {
                        Toast.makeText(MainActivity.this, "Preencha os campos de E-mail e Senha", Toast.LENGTH_LONG).show();
                    }

                }
            });

        }

    }

    private void validarLogin() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAuth();
        autenticacao.signInWithEmailAndPassword(usuario.getEmail().toString(), usuario.getSenha().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    abrirTelaAdministrador();
                    Toast.makeText(MainActivity.this, "Login efetuado com sucesso", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Usuário ou Senha inválidos! Tente novamente", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void abrirTelaAdministrador() {
        Intent intent = new Intent(MainActivity.this, CadastroUsuarioActivity.class);
        startActivity(intent);
    }

    public Boolean usuarioLogado() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            return true;

        } else {
            return false;
        }

    }

    public void abrirNovaActivity(Intent intent) {
        startActivity(intent);
    }



}
