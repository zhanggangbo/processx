package com.github.processx.dal.daointerface;

import com.github.processx.dal.dataobjects.ProcessGlobalVariableDO;
import java.util.List;

public interface ProcessGlobalVariableDOMapper extends ProcessGlobalVariableDOMapperBase {
  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table process_global_variable
   *
   * @mbggenerated
   */
  int deleteByPrimaryKey(Long id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table process_global_variable
   *
   * @mbggenerated
   */
  int insert(ProcessGlobalVariableDO record);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table process_global_variable
   *
   * @mbggenerated
   */
  ProcessGlobalVariableDO selectByPrimaryKey(Long id);

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table process_global_variable
   *
   * @mbggenerated
   */
  List<ProcessGlobalVariableDO> selectAll();

  /**
   * This method was generated by MyBatis Generator.
   * This method corresponds to the database table process_global_variable
   *
   * @mbggenerated
   */
  int updateByPrimaryKey(ProcessGlobalVariableDO record);
}