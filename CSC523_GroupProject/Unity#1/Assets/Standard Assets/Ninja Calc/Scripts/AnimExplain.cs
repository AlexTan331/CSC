using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
public class AnimExplain : MonoBehaviour
{
    public Animator Explain;
    public void start()
    {
        Debug.Log("click");
        Explain.SetBool("IsStart", true);
       // Explain.SetBool("IsStart", false);
    }
}
