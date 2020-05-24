﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ActivateText : MonoBehaviour
{
    public TextAsset theText;
    public int startLine;
    public int endLine;
    public TextBoxManager theTextBox;

    public bool destroyWhenActivated;
    // Start is called before the first frame update
    void Start()
    {
        theTextBox = FindObjectOfType<TextBoxManager>();

    }

    // Update is called once per frame
    void Update()
    {
        
    }

    void OnTriggerEnter2D(Collider2D other)
    {
        if (other.tag == "Ninja")
        {
            theTextBox.ReloadScript(theText);
            theTextBox.currentLine = startLine;
            theTextBox.endAtLine = endLine;
            theTextBox.enableTextBox();
        }
        if (destroyWhenActivated)
        {
            Destroy(gameObject);
        }
    }
}
