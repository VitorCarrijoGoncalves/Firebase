package senai.mobile.com.br.firebase.Helper;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferencias {

    private Context context;
    private SharedPreferences preferences;
    private String NOME_ARQUIVO;
    private int NODE = 0;
    private SharedPreferences.Editor editor;

    private final String EMAIL_USUARIO_LOGADO = "emailUsuarioLogado";
    private final String SENHA_USUARIO_LOGADO = "senha_usuario_logado";

    public Preferencias(Context contextoParametro) {

        context = contextoParametro;

        // associe o nosso preferences.edit()

        editor = preferences.edit();

    }


    public void salvarUsuarioPreferencias(String email, String senha) {

        // salvar dentro do nosso arquivo de preferencias o email e senha do usuario

        editor.putString(EMAIL_USUARIO_LOGADO, email);
        editor.putString(SENHA_USUARIO_LOGADO, senha);
        editor.commit();

    }

    public String getEmailUsuarioLogado() {
        return preferences.getString(EMAIL_USUARIO_LOGADO, null);
    }

    public String getSenhaUsuarioLogado() {
        return preferences.getString(SENHA_USUARIO_LOGADO, null);
    }

}
