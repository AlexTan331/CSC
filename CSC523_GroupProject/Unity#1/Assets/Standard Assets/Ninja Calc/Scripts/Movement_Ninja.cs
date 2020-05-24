using UnityEngine;
using System.Collections;
using System.Collections.Generic;
public class Movement_Ninja : MonoBehaviour
{
    public float runspeed = 4f;
    public float JumpForce = 500f;
    public Rigidbody2D rb;
    private bool m_FacingRight = true;  // For determining which way the player is currently facing.
    public Animator m_Anim;            // Reference to the player's animator component.
    bool jump;
    bool crouch;
    public bool canMove;

	public void Awake()
	{
		rb = gameObject.GetComponent<Rigidbody2D>();
		m_Anim = gameObject.GetComponent<Animator>();
		jump = false;
		crouch = false;

	}
	// Update is called once per frame
	void FixedUpdate()
	{
        if (!canMove)
            return;

	   m_Anim.SetFloat("speed", Mathf.Abs(rb.velocity.x));
	   if (Input.GetKey(KeyCode.RightArrow))
	   {
	   	//Move the Rigidbody to the right constantly at speed you define (the red arrow axis in Scene view)
            rb.velocity = new Vector2(runspeed, 0.0f);
		  if (!m_FacingRight)
	       	{
	       	     Flip();
			}
	   }

	   if (Input.GetKey(KeyCode.LeftArrow))
	   {
	       //Move the Rigidbody to the right constantly at speed you define (the red arrow axis in Scene view)
		  rb.velocity = new Vector2(-runspeed, 0.0f);
	   	  if (m_FacingRight)
			 Flip();
	   }

	   //Click the mouse or tap the screen to change the animation
	   if (Input.GetButtonDown("Jump"))
	   	  jump = true;

	   //Otherwise the GameObject cannot jump.
	   else
		  jump = false;

		//If the GameObject is not jumping, send that the Boolean “Jump” is false to the Animator. The jump animation does not play.
	   if (jump == false)
		  m_Anim.SetBool("IsJumping", false);

	   //The GameObject is jumping, so send the Boolean as enabled to the Animator. The jump animation plays.
	   if (jump == true)
	   {
		  m_Anim.SetBool("IsJumping", true);
	       FindObjectOfType<AudioManager>().Play("Jump");
		  rb.AddForce(new Vector2(0f, JumpForce));

	   }



	   if (Input.GetButtonDown("Crouch"))
	   {
	   	  crouch = true;
	   } 
	   else if (Input.GetButtonUp("Crouch"))
	   {
		  crouch = false;
	   }

	   m_Anim.SetBool("IsCrouching", crouch);

    }

    private void Flip()
    {
	   // Switch the way the player is labelled as facing.
	   m_FacingRight = !m_FacingRight;

	   // Multiply the player's x local scale by -1.
	   Vector3 theScale = transform.localScale;
	   theScale.x *= -1;
	   transform.localScale = theScale;
    }

    public void BackUp()
    {
        if (m_FacingRight = !m_FacingRight)
            rb.AddForce(new Vector2(300f, JumpForce));
        else
            rb.AddForce(new Vector2(-300f, JumpForce));

    }

}
