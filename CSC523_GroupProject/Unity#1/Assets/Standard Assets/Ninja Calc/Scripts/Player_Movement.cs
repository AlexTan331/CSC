using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Player_Movement : MonoBehaviour
{
    public CharacterController2D controller;
    public StaticQuestion player;
    public Animator m_Anim;
    bool Jump = false;
    bool Crouch = false;
    float HorizontalMove = 0f;
    public float RunSpeed = 40f;
    
    void Start()
    {
        player = FindObjectOfType<StaticQuestion>();
    }
    // Update is called once per frame
    void Update()
    {
        if (player.canMove ==false)
        {
            HorizontalMove = Input.GetAxisRaw("Horizontal") * RunSpeed * 0;
        }
        else
        {
            HorizontalMove = Input.GetAxisRaw("Horizontal") * RunSpeed;
        }

        m_Anim.SetFloat("speed", Mathf.Abs(HorizontalMove));

        if (Input.GetButtonDown("Jump"))
        {
            Jump = true;
            m_Anim.SetBool("IsJumping", true);
            FindObjectOfType<AudioManager>().Play("Jump");
        }

        if (Input.GetButtonDown("Crouch"))
        {
            Crouch = true;
        }
        else if (Input.GetButtonUp("Crouch"))
        {
            Crouch = false;
        }
    }


    public void onLanding()
    {
        m_Anim.SetBool("IsJumping", false);
        FindObjectOfType<AudioManager>().Play("Land");
    }

    public void onCrouching(bool isCrouching)
    {
        m_Anim.SetBool("IsCrouching", isCrouching); 
    }
    void FixedUpdate()
    {
        controller.Move(HorizontalMove * Time.fixedDeltaTime, Crouch, Jump);
        Jump = false;
    }
    public void Backup()
    {
        Jump = true;
        m_Anim.SetBool("IsJumping", true);
    }
}
