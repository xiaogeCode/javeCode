package xiaoge.model;

import java.io.Serializable;

/**
 * ʵ���ࣺŮ����
 * 
 * @author AlanLee
 * 
 */
public class Goddess implements Serializable
{
  private static final long serialVersionUID = 1L;
 
  /**
   * Ψһ����
   */
  private Integer id;
  /**
   * ����
   */
  private String name;
  /**
   * �ֻ�����
   */
  private String mobie;
  /**
   * �����ʼ�
   */
  private String email;
  /**
   * ��ͥסַ
   */
  private String address;
 
  public Integer getId()
  {
    return id;
  }
 
  public void setId(Integer id)
  {
    this.id = id;
  }
 
  public String getName()
  {
    return name;
  }
 
  public void setName(String name)
  {
    this.name = name;
  }
 
  public String getMobie()
  {
    return mobie;
  }
 
  public void setMobie(String mobie)
  {
    this.mobie = mobie;
  }
 
  public String getEmail()
  {
    return email;
  }
 
  public void setEmail(String email)
  {
    this.email = email;
  }
 
  public String getAddress()
  {
    return address;
  }
 
  public void setAddress(String address)
  {
    this.address = address;
  }
}
