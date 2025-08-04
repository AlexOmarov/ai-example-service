# Contributing Guidelines

We welcome contributions to the project! Please follow these guidelines to ensure smooth collaboration.

## Branch Naming

Create a new branch for each task, following the format: `feature/<QUEUE_CODE>-<TICKET_NUMBER>`.

Example: `feature/ABC-123`

## Steps for Contributing

Before merging into the `master` (or `main`) branch, ensure the following:

### 1. **Code Review**
- Ensure your code follows the project's coding standards and conventions.
- Perform a self-review of your changes before submitting.

### 2. **Unit Tests**
- Write new unit tests for any new functionality or modified code.
- Ensure existing tests are not broken.

### 3. **README Update**
- Update the README if significant changes were made that affect setup or usage.
- Ensure clarity and completeness.

### 4. **Documentation & UML**
- Update documentation and UML diagrams if workflows or system architecture have been modified.
- Keep documentation accurate and up to date.

### 5. **Database Changes**
- If the data model has been altered, update the database schema and provide migration scripts (if applicable).

## Additional Best Practices

### Code Quality
- **Follow code style conventions**: Stick to consistent formatting and naming conventions.
- **Write clear, meaningful commit messages**: Follow the format `Type: short description`. Example: `Fix: correct typo in user authentication`.
- **Keep commits small**: Make each commit focused on a single task or feature.
- **Review your code**: Before submitting, perform a self-review to catch errors or inconsistencies.

### Pull Requests
- **Provide context in PR descriptions**: Describe what has changed, why, and any additional context.
- **Link to related issues/tickets**: Mention related GitHub/GitLab issues or tickets in your PR description.
- **Request a review**: Assign at least one reviewer to your PR to ensure code quality.

### Versioning
- **Semantic Versioning**: Follow [Semantic Versioning](https://semver.org/) for version updates. Update the version number in the changelog accordingly.

### Testing
- **Run tests locally**: Ensure all tests pass before pushing your changes.
- **Use continuous integration**: All code must pass CI checks before merging.

### Security
- **Avoid sensitive data**: Do not commit passwords, API keys, or other sensitive information.
- **Audit dependencies**: Regularly check for outdated or insecure dependencies.

---

By adhering to these guidelines, we can maintain high code quality and smooth collaboration. 

Thank you for contributing! 🌟
