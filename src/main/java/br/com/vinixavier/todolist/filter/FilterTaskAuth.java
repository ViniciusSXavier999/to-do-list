package br.com.vinixavier.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.vinixavier.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servletPath = request.getServletPath();

        if (servletPath.equals("/tasks/")) {

            // PEGAR A AUTENTICAÇÃO (USUARIO E SENHA)
            /*
             * Isso é semelhante a eu estar colocando dentro do meu Authorization as minhas
             * informações
             */
            var authorization = request.getHeader("Authorization");

            /*
             * Eu quero que você pegue o meu basic e você calcule qual o lenght dela, vai
             * pegar do inicio da palavra e vai
             * tirar 5 caracter e o trim() vai fazer com que ele remova todos os espaços que
             * eu tenho.
             */
            var authEncoded = authorization.substring("Basic".length()).trim();

            // Estou fazendo o decode aqui e criando um array de byte
            byte[] authDecode = Base64.getDecoder().decode(authEncoded);

            // Agora vou converter meu array de bytes para String
            var authString = new String(authDecode);

            // Dividindo minha string em pedaços com o método split
            String[] credentials = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];

            // VALIDAR USUARIO
            var user = repository.findByUsername(username);

            if (user == null) {
                response.sendError(401, "Usuário sem autorização");
            } else {
                // VALIDAR SENHA
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

                // variavel booleana que retorna true ou false
                if (passwordVerify.verified) {

                    /*Esse setAtribute recebe um nome do atributo,  nesse caso eu vou settar um atributo chamado Iduser com o valor
                     * do meu id
                    */
                    request.setAttribute("idUser", user.getId());
                    // SEGUE VIAGEM
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }

            }

        } else {
              filterChain.doFilter(request, response);
        }

    }

}
