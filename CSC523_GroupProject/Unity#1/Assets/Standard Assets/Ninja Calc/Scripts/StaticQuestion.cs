using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class StaticQuestion : MonoBehaviour
{

    public GameObject[] questions;
    private int index;
    private int KeyNumber;
    public bool canMove;

    void Start()
    {
        for (int i=0; i<questions.Length;i++)
        {
            questions[i].gameObject.SetActive(false);
        }
    }

    void OnTriggerEnter2D(Collider2D other)
    {
        
        switch (other.tag)
        {
            case "Key_1":
                canMove = false;
                KeyNumber = 0;
                questions[KeyNumber].gameObject.SetActive(true);
                break;
            case "Key_2":
                canMove = false;
                KeyNumber = 1;
                questions[KeyNumber].gameObject.SetActive(true);
                break;
            case "Key_3":
                canMove = false;
                KeyNumber = 2;
                questions[KeyNumber].gameObject.SetActive(true);
                break;
            case "Key_4":
                canMove = false;
                KeyNumber = 3;
                questions[KeyNumber].gameObject.SetActive(true);
                break;
            case "Key_5":
                canMove = false;
                KeyNumber = 4;
                questions[KeyNumber].gameObject.SetActive(true);
                break;
            case "Key_6":
                canMove = false;
                KeyNumber = 5;
                questions[KeyNumber].gameObject.SetActive(true);
                break;
            case "Key_7":
                canMove = false;
                KeyNumber = 6;
                questions[KeyNumber].gameObject.SetActive(true);
                break;
            case "Key_8":
                canMove = false;
                KeyNumber = 7;
                questions[KeyNumber].gameObject.SetActive(true);
                break;
            default:
                break;
        }
       

    }

    public void disableQuestionBox_static()
    {
        questions[KeyNumber].gameObject.SetActive(false);
        canMove = true;
    }
}
