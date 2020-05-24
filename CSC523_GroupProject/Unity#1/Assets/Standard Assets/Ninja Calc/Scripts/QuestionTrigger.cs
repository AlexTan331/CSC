using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class QuestionTrigger : MonoBehaviour
{
    void OnTriggerEnter2D(Collider2D other)
    {
        if (other.tag == "Key")
        {
            FindObjectOfType<QuestionManager>().TriggertheQuestion();
        }

    }
}
