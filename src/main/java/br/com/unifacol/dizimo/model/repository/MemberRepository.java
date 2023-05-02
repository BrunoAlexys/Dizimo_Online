package br.com.unifacol.dizimo.model.repository;

import br.com.unifacol.dizimo.model.entities.Member;
import br.com.unifacol.dizimo.model.interfaces.repository.IMemberRepository;
import br.com.unifacol.dizimo.model.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberRepository implements IMemberRepository {
    private EntityManager manager;
    private final AccountFinder accountFinder = new AccountFinder();

    public MemberRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void create(Member member) throws SQLException {
        try {
            this.manager.getTransaction().begin();
            this.manager.persist(member);
            if (manager.getTransaction().isActive()) {
                if (manager.getTransaction().getRollbackOnly()) {
                    manager.getTransaction().rollback();
                } else {
                    manager.getTransaction().commit();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao cadastrar membro", e);
        }
    }

    @Override
    public void update(Member member) throws SQLException {
        try {
            Member memberfound = accountFinder.searchMemberByCpfAndPassword(member.getCpf(), member.getPassword());
            if (memberfound != null) {
                manager.getTransaction().begin();
                memberfound = manager.merge(memberfound);
                memberfound.setName(member.getName());
                memberfound.setAge(member.getAge());
                memberfound.setDateOfBirth(member.getDateOfBirth());
                memberfound.setCpf(member.getCpf());
                memberfound.setSex(member.getSex());
                memberfound.setActive(member.getActive());
                memberfound.setPassword(member.getPassword());
                memberfound.setAddress(member.getAddress());
                memberfound.setAccountMember(member.getAccountMember());
                manager.flush();
                if (manager.getTransaction().isActive()) {
                    if (manager.getTransaction().getRollbackOnly()) {
                        manager.getTransaction().rollback();
                    } else {
                        manager.getTransaction().commit();
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao alterar o membro", e);
        } finally {
            manager.close();
        }
    }

    @Override
    public void delete(String cpf, Integer password) throws SQLException {
        try {
            Member memberfound = accountFinder.searchMemberByCpfAndPassword(cpf, password);
            if (memberfound == null) {
                throw new NoResultException("Não foi encontrada nenhuma conta com CPF: " + cpf);
            }
            manager.getTransaction().begin();
            memberfound = manager.merge(memberfound);
            manager.remove(memberfound);
            if (manager.getTransaction().isActive()) {
                if (manager.getTransaction().getRollbackOnly()) {
                    manager.getTransaction().rollback();
                } else {
                    manager.getTransaction().commit();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir o membro", e);
        } finally {
            manager.close();
        }
    }

    @Override
    public List<Member> listMembers() throws SQLException {
        try {
            String jpql = "SELECT m FROM Member m ORDER BY m.name";
            TypedQuery<Member> query = manager.createQuery(jpql, Member.class);
            List<Member> members = query.getResultList();
            if (members.isEmpty()) {
                System.out.println("Não há membros cadastrados.");
            }
            return members;
        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new SQLException("Erro ao listar membros: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar os membros", e);
        } finally {
            manager.close();
        }
    }

    @Override
    public List<Member> listmembersCpfAndPassword(String cpf, Integer password) throws SQLException {
        try {
            Member memberfound = accountFinder.searchMemberByCpfAndPassword(cpf, password);
            List<Member> listMembers = new ArrayList<>();
            if (memberfound != null) {
                listMembers.add(memberfound);
            }
            return listMembers;
        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new SQLException("Erro ao listar o membro pelo CPF: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar o membro", e);
        } finally {
            manager.close();
        }
    }
}
