using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.EventSystems;
public class HelpMenuManager : MonoBehaviour
{
    public GameObject[] helpButton;
    public GameObject[] Examples;
    private Queue<string> sentences;
    public Text DialogueText;
    public Animator DialogueAnim;
    private string HelpButtonName;

    // Start is called before the first frame update
    void Start()
    {        
        for (int i=0;i<helpButton.Length;i++)
        {
            helpButton[i].gameObject.SetActive(false);      // 0.HelpMenu   1.MainMenuButton   2.ExampleButton
        }

        sentences = new Queue<string>();
    }
    public void StartDialogue (References dialogue)
    {

        HelpButtonName= EventSystem.current.currentSelectedGameObject.name;     //Get the topic name of helpbuttons 

        helpButton[1].gameObject.SetActive(false);
        helpButton[2].gameObject.SetActive(false);

        for (int i=0; i<Examples.Length;i++)
        {
            Examples[i].gameObject.SetActive(false);        
        }


        DialogueAnim.SetBool("IsOpen", true);
        sentences.Clear();

        foreach (string sentence in dialogue.sentences)
        {
            sentences.Enqueue(sentence);
        }

        DisplayNextSentence();

    }

    public void DisplayNextSentence()
    {
        if (sentences.Count ==1)
            helpButton[2].gameObject.SetActive(true);      //show ExampleButton 

        else if (sentences.Count ==0)
        {
            EndDialogue();
            return;
        }

        string sentence = sentences.Dequeue();
        StopAllCoroutines();                                    
        StartCoroutine(TypeSentence(sentence));

    } 

    IEnumerator TypeSentence(string sentence)
    {
        DialogueText.text = "";
        foreach (char letter in sentence.ToCharArray())         //display characters in sentences one by one
        {
            DialogueText.text += letter;
            yield return null;
        }
    }

    void EndDialogue()
    {
        DialogueAnim.SetBool("IsOpen", false);
        
    }

    public void CloseHelpWindow()
    {
        for (int i = 0; i < helpButton.Length; i++)
        {
            helpButton[i].gameObject.SetActive(false);      // 0.HelpMenu   1.MainMenuButton   2.ExampleButton
        }
    }

    public void showHelp()
    {
        helpButton[0].gameObject.SetActive(true);
        helpButton[1].gameObject.SetActive(true);
    }
    public void closeHelp()
    {
        for (int i = 0; i < helpButton.Length; i++)
        {
            helpButton[i].gameObject.SetActive(false);      // 0.HelpMenu   1.MainMenuButton   2.ExampleButton
        }
    }

    public void showExample()                               
    {
        if (helpButton[2].gameObject.activeInHierarchy)
        {
            if (HelpButtonName == "Limit_HelpButton")
                Examples[0].gameObject.SetActive(true);
            else if (HelpButtonName == "Productrule_HelpButton")
                Examples[1].gameObject.SetActive(true);
            else if (HelpButtonName == "Quotientrule_HelpButton")
                Examples[2].gameObject.SetActive(true);
            else if (HelpButtonName == "HigherDerivative_HelpButton")
                Examples[3].gameObject.SetActive(true);
            else if (HelpButtonName == "PowerRule(Integral)_HelpButton")
                Examples[4].gameObject.SetActive(true);

        }
    }
}
