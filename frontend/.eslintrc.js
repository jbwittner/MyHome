module.exports = {
    root: true, // Make sure eslint picks up the config at the root of the directory
    parser: '@typescript-eslint/parser',
    parserOptions: {
        ecmaVersion: 2015, // Use the latest ecmascript standard
        sourceType: 'module', // Allows using import/export statements
        ecmaFeatures: {
            jsx: true // Enable JSX since we're using React
        }
    },
    globals: {
        React: true,
        JSX: true,
    },
    settings: {
        react: {
            version: 'detect' // Automatically detect the react version
        }
    },
    env: {
        browser: true, // Enables browser globals like window and document
        amd: true, // Enables require() and define() as global variables as per the amd spec.
        node: true, // Enables Node.js global variables and Node.js scoping.
        es6: true,
        jest: true
    },
    extends: [
        'eslint:recommended',
        'plugin:react/recommended',
        'plugin:jsx-a11y/recommended',
        'plugin:prettier/recommended' // Make this the last element so prettier config overrides other formatting rules
    ],
    rules: {
        'prettier/prettier': 'off', // Use our .prettierrc file as source
        'react/display-name': 'off',
        'no-useless-escape': 'off',
        'no-unused-vars': ['error', {
            args: 'none',
          }],
        "no-restricted-imports": [
            "error",
            {
              "patterns": ["@material-ui/*/*/*", "!@mui/material/test-utils/*"]
            }
          ]
    }
};