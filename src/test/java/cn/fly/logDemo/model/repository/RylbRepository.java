package cn.fly.logDemo.model.repository;

import cn.fly.logDemo.model.Dmb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author fly
 * @date 2023/3/13
 * @description
 */

public interface RylbRepository extends JpaRepository<Dmb, String> {

    List<Dmb> findByDmid(String dmid);

}
