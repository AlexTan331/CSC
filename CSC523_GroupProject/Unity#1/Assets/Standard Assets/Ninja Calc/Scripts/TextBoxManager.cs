using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class TextBoxManager : MonoBehaviour
{

    public GameObject textBox;
    public Text theText;

    public TextAsset textFile;
    public string[] textLines;

    public int currentLine;
    public int endAtLine;

    public bool isActive;

    public Player_Movement player;

    // Use this for initialization
    void Start()
    {
        player = FindObjectOfType<Player_Movement>();

        if (textFile != null)
        {
            textLines = (textFile.text.Split('\n'));
        }
        if (endAtLine == 0)
        {
            endAtLine = textLines.Length - 1;
        }
        if (isActive)
            enableTextBox();
        else
            disableTextBox();

    }

    void Update()
    {
        if (!isActive)
            return;

        theText.text = textLines[currentLine];
        if (Input.GetKeyDown(KeyCode.Return))
        {
            currentLine += 1;
        }
        if (currentLine > endAtLine)
        {
            disableTextBox();
        }
    }
    public void enableTextBox()
    {
        textBox.SetActive(true);
        isActive = true;
 
       // player.canMove = false;

    }

    public void disableTextBox()
    {
        textBox.SetActive(false);
        isActive = false;

       // player.canMove = true;
        
    }
    public void ReloadScript(TextAsset theText)
    {
        if (theText != null)
        {
            textLines = new string[1];
            textLines = (theText.text.Split('\n'));
        }
    }
}