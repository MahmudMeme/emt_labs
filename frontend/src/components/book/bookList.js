import React from "react";
import ReactPaginate from "react-paginate";
import {Link} from 'react-router-dom';
import BookTerm from "./bookTerm";
import "./bookList.css"
import {useNavigate} from 'react-router-dom';
import {useHistory} from 'react-router-dom';


class BookList extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            page: 0,
            size: 5
        }
        this.onFormSubmit = this.onFormSubmit.bind(this);
    }

    onFormSubmit(e) {
        e.preventDefault();
        // const name = this.formData.q;
        const name = e.target.elements.q.value;
        if (name){
            this.props.onSearchBooks(name);
        }else{
            this.props.onEmptyName();
        }

        // if (e.target.elements.q.value != null) {
        //     const name = e.target.elements.q.value;
        //     this.props.onSearchBooks(name);
        // }
        // else {
        //     this.props.onEmptyName();
        // }
        //this.props.onSearchBooks(name);
        //history.push("/books");
        //useNavigate('/books')
    }

    // onFormSubmit = (e) => {
    //     e.preventDefault();
    //     const name = this.formData.q;
    //
    //     this.props.onSearchBooks(name);
    //     // history.push("/products");
    //     useNavigate('/books');
    // }
    render() {
        const offset = this.state.size * this.state.page;
        const nextPageOffset = offset + this.state.size;
        const pageCount = Math.ceil(this.props.books.length / this.state.size)
        const books = this.getBookPage(offset, nextPageOffset);

        return (
            <div className={"container mm-4 mt-5"}>
                <div className={"row"}>
                    <div className={"table-responsive"}>
                        <div className={"row"}>
                            <form onSubmit={this.onFormSubmit}>
                                <label htmlFor="searchInput">Search:</label>
                                <input type="text" id="searchInput" name="q" placeholder="Enter your search query"/>
                                <button type="submit">Search</button>
                            </form>
                        </div>
                        <table className={"table table-striped"}>
                            <thead>
                            <tr>
                                <th scope={"col"}>Name</th>
                                <th scope={"col"}>Category</th>
                                <th scope={"col"}>Author</th>
                                <th scope={"col"}>availableCopies</th>
                            </tr>
                            </thead>
                            <tbody>
                            {books}
                            </tbody>
                        </table>
                    </div>


                    <div className="col mb-3">
                        <div className="row">
                            <div className="col-sm-12 col-md-12">
                                <Link className={"btn btn-block btn-dark"} to={"/books/add"}>Add new book</Link>
                            </div>
                        </div>
                    </div>

                </div>
                <ReactPaginate previousLabel={"back"}
                               nextLabel={"next"}
                               breakLabel={<a href="/#">...</a>}
                               breakClassName={"break-me"}
                               pageClassName={"ml-2 paginate-item"}
                               pageCount={pageCount}
                               marginPagesDisplayed={2}
                               pageRangeDisplayed={5}
                               onPageChange={this.handlePageClick}
                               containerClassName={"pagination m-4 justify-content-center"}
                               activeClassName={"active"}/>
            </div>
        );
    }

    handlePageClick = (data) => {
        let selected = data.selected;
        console.log(selected)
        this.setState({
            page: selected
        })
    }
    getBookPage = (offset, nextPageOffset) => {
        console.log(offset, nextPageOffset)
        return this.props.books.map((term, index) => {
            return (
                <BookTerm term={term} onDelete={this.props.onDelete}
                          onEdit={this.props.onEdit}
                          onRent={this.props.onRent}
                          onMarkUsability={this.props.onMarkUsability}
                />
            );
        }).filter((product, index) => {
            return index >= offset && index < nextPageOffset;
        })
    }
}

export default BookList