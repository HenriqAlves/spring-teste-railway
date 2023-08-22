package com.lucasangelo.todosimple.services;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasangelo.todosimple.models.User;
import com.lucasangelo.todosimple.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    @Autowired //*Substitui o construtor para ter acesso aos repositories, pois são interfaces e n classes */
    private UserRepository userRepository;

    public User findById(Long id){

        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(()-> new RuntimeException(
            "Usuário não encontrado!" + id + ", Tipo:" + User.class.getName()
        )); //* retorna se não estiver vaio */
    }


    @Transactional
    public User create(User objeto){
        objeto.setId(null);
        objeto = this.userRepository.save(objeto);
        return objeto;
    }
    
    @Transactional //*Sempre utilizar quandof for fazer inserção no banco */
    public User update(User objeto){
        User newObjeto = findById(objeto.getId());
        newObjeto.setPassword(objeto.getPassword());
        return this.userRepository.save(newObjeto);
    }

    public void delete(Long id){
        findById(id);
        try {
           this.userRepository.deleteById(id); 
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");
        }
    }


}
