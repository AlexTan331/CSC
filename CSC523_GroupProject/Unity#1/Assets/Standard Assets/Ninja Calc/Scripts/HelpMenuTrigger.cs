using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class HelpMenuTrigger : MonoBehaviour
{
    public References dialogue; 

    public void TriggerDialogue()
    {
        FindObjectOfType<HelpMenuManager>().StartDialogue(dialogue);
    }
}
